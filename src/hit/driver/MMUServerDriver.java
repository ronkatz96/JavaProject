package hit.driver;

import java.io.IOException;

import hit.controller.MMULogFileController;

public class MMUServerDriver {

	public static void main(String[] args) throws IOException {
		new MMULogFileController().start();
		}
}
