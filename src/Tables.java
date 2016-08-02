import java.net.URL;

import javax.swing.JTable;

import com.jaunt.Element;
import com.jaunt.Elements;
import com.jaunt.NotFound;
import com.jaunt.ResponseException;
import com.jaunt.UserAgent;

public class Tables {
	private Element perGameTable;
	private JTable table;
	private Object[][] data;
	private UserAgent site;
	
	public Tables(URL url){
		try {
			site = new UserAgent();
			site.visit(url.toString());
			this.setPerGameTable();
		} catch (ResponseException e) {
			e.printStackTrace();
		}
	}
	public Tables(String url){
		try {
			site = new UserAgent();
			site.visit(url);
			this.setPerGameTable();
		} catch (ResponseException e) {
			e.printStackTrace();
		}
	}
	
	public void setPerGameTable(){
		try{
			Object[] columnNames = new Object[30]; 
			perGameTable = site.doc.findEvery("<table id=per_game>");  
		    Elements rows = perGameTable.findEvery("<tr>");
		    data = new Object[rows.size()][30];
		    for(int i = 0; i < rows.size()-1; i++){
		    	Elements temp = rows.getElement(i).findEach("<td>");
		    	Object[] array = new Object[temp.size()];
		    	for(int j = 0; j < temp.size(); j++){
		    		String s = temp.getElement(j).getText();
		    		if(i == 0){
		    			columnNames[j] = (Object)s;
		    		}
		    		else{
		    			if(j != 0 && j != 2 && j != 3){
			    			array[j] = s;
			    		}
			    		else{
			    			array[j] = "";
			    		}
		    		}
		    	}
		    	if(i == 0){
		    		data[i] = columnNames;
		    	}
		    	else{
		    		data[i] = array;
		    	}
		    }
		    //System.out.println(columnNames[29]);
		    //table = new JTable(data, columnNames);
		}
		catch (NotFound e) {
			e.printStackTrace();
		}
	}
	public JTable getPerGameTable(){
		return this.table;
	}
	public void setUserAgent(UserAgent u){
		this.site = u;
	}
	public UserAgent getUserAgent(){
		return this.site;
	}
	
	
	
	public static void main(String[] args){
		new Tables("http://www.basketball-reference.com/players/n/nowitdi01.html");
	}
}
