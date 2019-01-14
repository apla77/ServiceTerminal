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
		IpTerminal ipTerminais = IpTerminal.find("ipTerminal = ?", ipTerminal).first();
		System.out.println(" 1 ************************* ipTerminal = " + ipTerminal);
		if(ipTerminais == null) {
			System.out.println("No if setIpterminal");
			IpTerminal ip = new IpTerminal();
			ip.ipTerminal = ipTerminal;
			ip.save();
			ok();
		}
		else {
			System.out.println("nada 1");
			notFound();
		}	
	}
	
	// Consumido pela Urna
	public static void finalizarVotacaoAtual(String status, String ipTerminal) { 
		IpTerminal ipTerminais = IpTerminal.find("ipTerminal = ?", ipTerminal).first();
		System.out.println(" 2 ************************* ipTerminal = " + ipTerminal);
		
		if(status.equals("finalizado") && ipTerminais != null) {
			System.out.println("No if finalizarVotacaoAtual");
			fimVoto = true;
			ok();
		}else {
			System.out.println("nada 2");
			fimVoto = false;
			ok();
		}
	} 
	
	// Consumido pela Terminal
	public static void confirmarVotacaoAtual(String ipTerminal) {
		IpTerminal ipTerminais = IpTerminal.find("ipTerminal = ?", ipTerminal).first();
		System.out.println(" 3 ************************* ipTerminal = " + ipTerminal);
		if(fimVoto == true && ipTerminais != null) {
			System.out.println("No if confirmarVotacaoAtual");
			renderJSON(true);
		}
		else {
			System.out.println("nada 3");
			renderJSON(false);
		}
			
	}
	
	// Consumido pela Urna
    public static void tempoParaUrna(Long codUrna, String ipTerminal){	
    	IpTerminal ipTerminais = IpTerminal.find("ipTerminal = ?", ipTerminal).first();	
    	System.out.println(" 4 ************************* ipTerminal = " + ipTerminal);
   		if(ipTerminais != null) 
   		{   	
   			System.out.println("No if tempoParaUrna");
   	   	    pedidoTempo = true;
   	   	    ok();
   		}
   		else{
   			System.out.println("nada 4");
   			pedidoTempo = false; 
   			ok();
   		}	
    } 
    
    // Consumido pela Terminal
    public static void addTempo(String ipTerminal){
    	 IpTerminal ipTerminais = IpTerminal.find("ipTerminal = ?", ipTerminal).first();	
    	 System.out.println(" 5 ************************* ipTerminal = " + ipTerminal);
    	 if(pedidoTempo == true && ipTerminais != null) {
    		 System.out.println("No if addTempo");
    		 pedidoTempo = true;
    		  renderJSON(pedidoTempo);  
    	  }
    	 else {
    		 pedidoTempo = false;
   		  	 renderJSON(pedidoTempo); 
    	 }
      }    
}
