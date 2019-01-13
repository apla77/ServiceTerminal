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
	private static String ip_urnaVoto = "";
	private static String ip_urnaTempo = "";
	
	public static void finalizarVotacaoAtual(String status, String ipUrna) { 
		ip_urnaVoto = ipUrna;
		if(status.equals("finalizado") && ipUrna != "") {
			fimVoto = true;
		}
		ok();
	}
	
	public static void confirmarVotacaoAtual(String ipUrna) {
		if(fimVoto == true && ipUrna == ip_urnaVoto) {
			fimVoto = false;
			ip_urnaVoto = "";
			renderJSON(true);
		}
		else
			renderJSON(false);
	}
	
    public static void tempoParaUrna(int codUrna, String ipUrna){	
    	ip_urnaTempo = ipUrna;
   		TempoUrna tempUrna = TempoUrna.find("codUrna = ?", codUrna).first();
   		if(tempUrna == null && ipUrna == ip_urnaTempo) 
   		{   			
   			TempoUrna tempoUrna = new TempoUrna();
   			tempoUrna.data = new Date();
   			tempoUrna.codUrna = codUrna;
   			tempoUrna.tempoTotal = 30;
   			tempoUrna.save();  	
   	   	    pedidoTempo = true;
   	   	    ok();
   		}
   		else if(ipUrna == ip_urnaTempo){
   			tempUrna.tempoTotal += 30;
   			tempUrna.save();  
   			pedidoTempo = true;
   			ok();
   		}	
    }
    
     public static void addTempo(String ipUrna){

    	 if(pedidoTempo == true && ipUrna == ip_urnaTempo) {
    		  pedidoTempo = false; 
    		  ip_urnaTempo = "";
    		  renderJSON(true);  
    	  }
    	 else
    		 renderJSON(false);
      }    
}
