//Hw03.java

import java.io.*;
import java.util.Scanner;

public class Hw03{


	public static void main(String[] args){
		
		int[] Hours;   //weight
		int[] Points;  //value
		int[][] teamWin;
		
		File file = null;
		String filename;
		
		if(0 < args.length){
			filename = args[0];
			file = new File(filename);
			System.out.print(filename);
		}
		else{
			System.out.println("Nothing there dude!");
			System.exit(0);
		}
		
		try{
			Scanner input = new Scanner(file);
			
			int numProbs = input.nextInt();
			Hours = new int[numProbs];
			Points = new int[numProbs];
			int numHours = input.nextInt();
			teamWin = new int[numProbs][numHours + 1];
			System.out.print(" has " + numProbs + " problems over " + numHours + " hours\n");
			
			while(input.hasNextLine()){
				
				int i = 0;
				System.out.println("Pr#\tTime\tPoints");
				while(i != numProbs){
					//read in hours and points
					int currHour = input.nextInt();
					//put into weight array
					Hours[i] = currHour;
					int currPoints = input.nextInt();
					Points[i] = currPoints;
					i++;
					System.out.print(i + "\t" + Hours[i-1] + "\t" + Points[i-1] + "\n");
					
				}
				System.out.println();
				
				//now to call the knapsack function
				for(int j = 0; j <= numHours + 1; j++){
					teamWin[0][j] = 0;
					System.out.println("I am in for loop");
				}
				for(i = 1; i <= numProbs; i++){
					teamWin[i][0] = 0;
				}
				//create values
				for(i = 1; i <= numProbs; i++){
					for(int j = 0; j <= numHours + 1; j++){
						if(Hours[i] <= j){
							if((Points[i] + teamWin[i-1][j - Hours[i]]) > teamWin[i-1][j]){
								teamWin[i][j] = (Points[i] + teamWin[i-1][j - Hours[i]]);
							}
							else{
								teamWin[i][j] = teamWin[i-1][j];
							}
						}
						else{
							teamWin[i][j] = teamWin[i-1][j];
						}
					}
				}
				//go back through to get values
				//first let's print to see what we have and itf it's working
				for(i = 1; i <= numProbs; i++){
					for(int j = 0; j <= numHours + 1; j++){
						System.out.print(teamWin[i][j] + "\t");
					}
					System.out.println();
				}
				
			}
		}
		catch(FileNotFoundException e){
			System.out.println("Try again");
		}
	}
	
	/*public void Input(File name){
	}
	
	public void Schedule(){
		
	}
	
	public void Printlist(){
	}*/





}