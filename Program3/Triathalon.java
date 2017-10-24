//Aidan McCoy CSC 349, Fall 2017

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Triathalon {

	public static void main(String[] args) throws IOException {

		int tempId, tempRuntime, tempSwimtime, tempBiketime, numCampers = 0;
		String nextLine;

		ArrayList<Camper> Campers = new ArrayList<Camper>();
		ArrayList<Camper> RBSorted = new ArrayList<Camper>();

		File file = new File(args[0]);
		Scanner sc = new Scanner(file);

		while (sc.hasNextLine()) {
			nextLine = sc.nextLine();

			tempId = Integer.parseInt(nextLine.substring(0, nextLine.indexOf(":")));
			tempSwimtime = Integer.parseInt(nextLine.substring(nextLine.indexOf("(") + 1, nextLine.indexOf(",")));
			tempRuntime = Integer.parseInt(nextLine.substring(nextLine.indexOf(",") + 1, nextLine.lastIndexOf(",")));
			tempBiketime = Integer.parseInt(nextLine.substring(nextLine.lastIndexOf(",") + 1, nextLine.indexOf(")")));

			Campers.add(new Camper(tempId, tempSwimtime, tempRuntime, tempBiketime));
			numCampers++;
		}

		for (Camper camper : Campers) {
			loop: if (RBSorted.isEmpty()) {
				RBSorted.add(camper);
			} else {
				for (int i = 0; i < RBSorted.size(); i++) {
					if (camper.getRunBiketime() > RBSorted.get(i).getRunBiketime()) {
						RBSorted.add(i, camper);
						break loop;
					}
				}
				RBSorted.add(camper);
			}
		}

		System.out.print("sequence: ");
		printList(RBSorted);
		printTime(RBSorted);
	}

	public static void printTime(ArrayList<Camper> list) {
		int total = 0, end = 0;
		for (int i = 0; i < list.size(); i++) {
			if (total + list.get(i).getTotaltime() > end) {
				end = total + list.get(i).getTotaltime();
			}
			total += list.get(i).getSwimtime();
		}
		System.out.println("\ncompletion time: " + end);

	}

	public static void printList(ArrayList<Camper> list) {
		System.out.print(list.get(0).getId());
		for (int i = 1; i < list.size(); i++) {
			System.out.print("," + list.get(i).getId());
		}
	}
}

