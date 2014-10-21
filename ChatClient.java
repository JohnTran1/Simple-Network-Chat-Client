import java.util.*;
import java.io.*;
import java.net.*;

/* @Author John Tran
 * CS380 - Project 2
 * Due: October 13th, 2014
 */

public class ChatClient {

	// Threading for updating the chat
	public static class updater extends Thread {
		public void run() {
			while (true) {
				try {
					updateChat();

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

	}

	static Socket client;
	static boolean auth = false;

	public static void main(String[] args) throws Exception {

		// default host and port
		client = new Socket("76.91.123.97", 22222);

		System.out.println("You are now connected to "
				+ client.getInetAddress());
		PrintStream out = new PrintStream(client.getOutputStream());
		Scanner keyboard = new Scanner(System.in);
		// nickname prompt upon connect
		System.out.println("Enter a nickname:");
		String nickName = keyboard.nextLine();
		out.println(nickName);

		System.out.println("Type to chat or 'exit' to quit:");
		updater chatUp = new updater();
		chatUp.start();

		while (true) {

			String input = keyboard.nextLine();

			// Type "exit" to exit client
			out.println(input);
			if (input.equalsIgnoreCase("exit")) {
				out.println("Client is now disconnecting.  Goodbye!");
				System.exit(0);
			}

		}

	}

	public static void updateChat() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(
				client.getInputStream()));
		String output = br.readLine();

		if (auth == false) {
			if (output.contentEquals("Name in use.")) {
				client.close();
				System.out.println("Name is in use, closing connection");
				System.exit(0);
			}
			auth = true;
			System.out.println(output);
		} else {
			System.out.println(output);
		}
	}
}
