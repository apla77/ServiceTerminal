package controllers;

import java.util.Date;
import com.google.gson.Gson;
import com.mysql.fabric.xmlrpc.base.Data;

import antlr.collections.List;
import models.TempoUrna;
import play.mvc.Controller;
import play.mvc.results.RenderJson;
import play.mvc.results.RenderTemplate;
import play.mvc.results.RenderText;

public class Terminais extends Controller { 
	private static boolean pedidoTempo = false;
	private static boolean fimVoto = false;
	private static String ip_terminalVoto = "";
	private static String ip_terminalTempo = "";
	
	public static void finalizarVotacaoAtual(String status, String ipTerminal) { 
		ip_terminalVoto = ipTerminal;
		if(status.equals("finalizado") && ipTerminal != "") {
			fimVoto = true;
		}
		ok();
	}
	
	public static void confirmarVotacaoAtual(String ipTerminal) {
		if(fimVoto == true && ipTerminal == ip_terminalVoto) {
			fimVoto = false;
			ip_terminalVoto = "";
			renderJSON(true);
		}
		else
			renderJSON(false);
	}
	
    public static void tempoParaUrna(int codUrna, String ipTerminal){	
    	ip_terminalTempo = ipTerminal;
   		TempoUrna tempUrna = TempoUrna.find("codUrna = ?", codUrna).first();
   		if(tempUrna == null && ipTerminal == ip_terminalTempo) 
   		{   			
   			TempoUrna tempoUrna = new TempoUrna();
   			tempoUrna.data = new Date();
   			tempoUrna.codUrna = codUrna;
   			tempoUrna.tempoTotal = 30;
   			tempoUrna.save();  	
   	   	    pedidoTempo = true;
   	   	    ok();
   		}
   		else if(ipTerminal == ip_terminalTempo){
   			tempUrna.tempoTotal += 30;
   			tempUrna.save();  
   			pedidoTempo = true;
   			ok();
   		}	
    }
    
     public static void addTempo(String ipTerminal){

    	 if(pedidoTempo == true && ipTerminal == ip_terminalTempo) {
    		  pedidoTempo = false; 
    		  ip_terminalTempo = "";
    		  renderJSON(true);  
    	  }
    	 else
    		 renderJSON(false);
      }    
}
