import java.util.*;

public class DPLL {

	
	public static void main(String[] args) {
		ArrayList<String> a = new ArrayList<>();
		a.add("p");
		a.add("q");

		ArrayList<String> b = new ArrayList<>();
		b.add("p");
		b.add("~q");

		ArrayList<String> c = new ArrayList<>();
		c.add("~p");
		c.add("q");
		
		ArrayList<String> d = new ArrayList<>();
		d.add("~p");
		d.add("~q");
		
		ArrayList<ArrayList<String>> delta = new ArrayList<ArrayList<String>>();
		delta.add(a);
		delta.add(b);
		delta.add(c);
		delta.add(d);
		
		print("DPLL algorithm", true);
		print("",true);
		print("Premisas: ",true);
		printArrayList(delta);
		
		print("\nProcedimiento: ",true);
		ArrayList<String> voc = vocabulary(delta);
		
		long start = System.currentTimeMillis();
		
		boolean satisfacible = dpll(delta,voc);
		
		long elapsedTimeMillis = System.currentTimeMillis()-start;
		
		if(satisfacible){
			print("\nSatisfacible\n",true);
		}else{
			print("\nNo satisfacible\n",true);
		}
		
		System.out.println("Timepo: " + elapsedTimeMillis + " ms");



	}
	
	public static boolean dpll(ArrayList<ArrayList<String>> delta, ArrayList<String> vocabulary){
		print("\nNext step",true);
		printArrayList(delta);
		
		String fi = "";
		
		if(delta.size() == 0){
			return true;
		}
		if(delta.contains(new ArrayList<String>())){
			return false;
		}
		
		Random rand = new Random();
		int randInt = rand.nextInt(vocabulary.size());
		
		fi = vocabulary.get(randInt);
		
		if(dpll(simplify(delta, fi),vocabulary)){
			return true;
		}else{
			return dpll(simplify(delta, negation(fi)),vocabulary);
		}	
	}
	
		
	public static ArrayList<ArrayList<String>> simplify(ArrayList<ArrayList<String>> delta, String fi){
		ArrayList<ArrayList<String>> newDelta = new ArrayList<ArrayList<String>>();
		
		for(int i = 0; i < delta.size(); i++){
			if(delta.get(i).contains(fi)){
				continue;
			}else if(delta.get(i).contains(negation(fi))){
				ArrayList<String> temp = (ArrayList<String>) delta.get(i).clone();
				temp.remove(negation(fi));
				newDelta.add(temp);
			}else{
				newDelta.add(delta.get(i));
			}
		}
		
		return newDelta;		
	}
	
	
	public static String negation(String toNegate){
		String newString = "";
		
		if(toNegate.length() == 1){
			newString += "~";
			newString += toNegate;
		}
		if(toNegate.length() == 2){
			newString = String.valueOf(toNegate.charAt(1));
		}
		
		return newString;
	}
	
	public static void printArrayList(ArrayList<ArrayList<String>> clauses){
		for(int i = 0; i < clauses.size(); i++){
			System.out.println(clauses.get(i).toString());
		}
	}
	
	public static void print(String toPrint, boolean nl){
		if(nl){
			System.out.println(toPrint);
		}else{
			System.out.print(toPrint);
		}
	}

	public static ArrayList<String> vocabulary(ArrayList<ArrayList<String>> delta) {
		HashSet<String> temp = new HashSet<>();
		ArrayList<String> result = new ArrayList<>();
		for (ArrayList<String> clause : delta) {
			for (String literal : clause) {
				if (literal.length() == 2) {
					temp.add(String.valueOf(literal.charAt(1)));
				}
				if(literal.length() == 1){
					temp.add(literal);
				}
			}
		}
		
		result.addAll(temp);
	
		return result;
	}
	
	
}
