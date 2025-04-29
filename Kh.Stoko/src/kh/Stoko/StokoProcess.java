package kh.Stoko;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class StokoProcess {

	private static StokoProcess instance = new StokoProcess();

	private StokoProcess() {
		super();
	}

	public static StokoProcess getInstance() {
		return instance;
	}

}
