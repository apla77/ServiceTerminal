package controllers;

import java.util.Date;
import com.google.gson.Gson;
import com.mysql.fabric.xmlrpc.base.Data;

import antlr.collections.List;
import models.IpTerminal;
import models.TempoUrna;
import play.mvc.Controller;
import play.mvc.results.RenderJson;
import play.mvc.results.RenderTemplate;
import play.mvc.results.RenderText;

public class Terminais extends Controller { 
	private static boolean pedidoTempo;
	private static boolean fimVoto;
	
	
	// Consumido pela Terminal 
	public static void setIpterminal(String ipTerminal) {
		System.out.println(" ip = " + ipTerminal);
		IpTerminal ipTerminais = IpTerminal.find("ipTerminal = ?", ipTerminal).first();
		if(ipTerminais == null) {
			IpTerminal ip = new IpTerminal();
			ip.ipTerminal = ipTerminal;
			ip.save();
			ok();
		}
		else {
			notFound();
		}	
	}
	
	// Consumido pela Urna
	public static void finalizarVotacaoAtual(String status, String ipTerminal) { 
		IpTerminal ipTerminais = IpTerminal.find("ipTerminal = ?", ipTerminal).first();
		
		if(status.equals("finalizado") && ipTerminais != null) {
			fimVoto = true;
			ok();
		}else {
			fimVoto = false;
			ok();
		}
	}
	
	// Consumido pela Terminal
	public static void confirmarVotacaoAtual(String ipTerminal) {
		IpTerminal ipTerminais = IpTerminal.find("ipTerminal = ?", ipTerminal).first();
		if(fimVoto == true && ipTerminais != null) {
			renderJSON(true);
		}
		else
			renderJSON(false);
	}
	
	// Consumido pela Urna
    public static void tempoParaUrna(Long codUrna, String ipTerminal){	
    	IpTerminal ipTerminais = IpTerminal.find("ipTerminal = ?", ipTerminal).first();	
   		if(ipTerminais != null) 
   		{   	
   	   	    pedidoTempo = true;
   	   	    ok();
   		}
   		else{
   			pedidoTempo = false; 
   			ok();
   		}	
    } 
    
    // Consumido pela Terminal
    public static void addTempo(String ipTerminal){
    	 IpTerminal ipTerminais = IpTerminal.find("ipTerminal = ?", ipTerminal).first();	
    	 if(pedidoTempo == true && ipTerminais != null) {
    		  renderJSON(true);  
    	  }
    	 else {
    		 renderJSON(false);
    	 }
      }    
}
