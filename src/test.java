import java.util.ArrayList;

import com.jaunt.Element;
import com.jaunt.Elements;
import com.jaunt.NotFound;
import com.jaunt.ResponseException;
import com.jaunt.UserAgent;

public class test {
	
	public test(){
		execute();
	}
	public void execute(){
		try{
			UserAgent userAgent = new UserAgent();
			userAgent.visit("http://www.basketball-reference.com/players/n/nowitdi01.html"); 
			Element perGameTable = userAgent.doc.findEvery("<table id=per_game>");  
			System.out.println(perGameTable.innerHTML());
		}
		catch(ResponseException e){
			  System.out.println(e);
		}
	}
	
	public static void main(String[] args){
		new test();
	}
}
