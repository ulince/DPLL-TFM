import java.util.*;

public class TFM {

	public static void main(String[] args) {
		ArrayList<String> a = new ArrayList<>();
		a.add("p");
		a.add("q");

		ArrayList<String> b = new ArrayList<>();
		b.add("~p");
		b.add("r");

		ArrayList<String> c = new ArrayList<>();
		c.add("~q");
		c.add("r");

		ArrayList<String> d = new ArrayList<>();
		d.add("~r");

		ArrayList<ArrayList<String>> delta = new ArrayList<ArrayList<String>>();
		delta.add(a);
		delta.add(b);
		delta.add(c);
		delta.add(d);
		
		
		print("Two Finger Method Algorithm\n", true);
		
		print("Premisas", true);
		printArrayList(delta);
		
		print("\nProcedimiento: \n", true);
		
		long start = System.currentTimeMillis();
		
		ArrayList<ArrayList<String>> result = tfm(delta);
		
		long elapsedTimeMillis = System.currentTimeMillis()-start;
		
		printArrayList(result);
		
		print("\nNo satisfacible",true);
		
		System.out.println("Timepo: " + elapsedTimeMillis + " ms");
				

	}
	
	public static ArrayList<ArrayList<String>> tfm(ArrayList<ArrayList<String>> delta){	
		ArrayList<ArrayList<String>> temp;
		
		while(!hasEmptyClause(delta)){
			//printArrayList(delta);
			temp = (ArrayList<ArrayList<String>>) delta.clone();
			for(int i = 0; i < delta.size(); i++){
				for(int j = 0; j < delta.size(); j++){
					temp.add(resolvents(delta.get(i), delta.get(j)));
				}
			}
			
			temp = clean(temp);
			delta = (ArrayList<ArrayList<String>>) temp.clone();	
		}	
		
		return delta;
	}

	public static ArrayList<String> resolvents(ArrayList<String> a,
			ArrayList<String> b) {
		boolean flag = false;
		ArrayList<String> c;
		ArrayList<String> A = (ArrayList<String>) a.clone();
		ArrayList<String> B = (ArrayList<String>) b.clone();
		for (int i = 0; i < A.size(); i++) {
			for (int j = 0; j < B.size(); j++) {
				if (A.get(i).length() == 1) {
					if (B.get(j).equals("~" + A.get(i))) {
						A.remove(i);
						B.remove(j);
						flag = true;
						break;
					}
				}
				if (A.get(i).length() == 2) {
					if (B.get(j).equals(String.valueOf(A.get(i).charAt(1)))) {
						A.remove(i);
						B.remove(j);
						flag = true;
						break;
					}
				}
			}
		}

		if (flag) {
			c = concat(A, B);
		} else {
			c = a;
		}

		return c;
	}

	public static ArrayList<String> concat(ArrayList<String> a,
			ArrayList<String> b) {
		ArrayList<String> result = new ArrayList<>();
		for (int i = 0; i < a.size(); i++) {
			if (!result.contains(a.get(i))) {
				result.add(a.get(i));
			}
		}
		for (int j = 0; j < b.size(); j++) {
			if (!result.contains(b.get(j))) {
				result.add(b.get(j));
			}
		}

		return result;

	}
	
	public static boolean hasEmptyClause(ArrayList<ArrayList<String>> clauses) {
		boolean result = false;

		for (ArrayList<String> clause : clauses) {
			if (clause.size() == 0) {
				result = true;
				break;
			}
		}

		return result;
	}
	
	public static ArrayList<ArrayList<String>> clean(
			ArrayList<ArrayList<String>> toClean) {	
		HashSet<ArrayList<String>> temp = new HashSet<ArrayList<String>>();
		temp.addAll(toClean);
		toClean.clear();
		toClean.addAll(temp);
		
		return toClean;
	}

	public static void printArrayList(ArrayList<ArrayList<String>> clauses){
		for(int i = 0; i < clauses.size(); i++){
			System.out.println(i + ": " + clauses.get(i).toString());
		}
	}
	
	public static void print(String toPrint, boolean nl){
		if(nl){
			System.out.println(toPrint);
		}else{
			System.out.print(toPrint);
		}
	}
	
}
