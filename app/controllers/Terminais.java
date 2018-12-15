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
	
    public static void tempoParaUrna(int codUrna){	
    	
   		TempoUrna tempUrna = TempoUrna.find("codUrna = ?", codUrna).first();
   		if(tempUrna == null) 
   		{   			
   			TempoUrna tempoUrna = new TempoUrna();
   			tempoUrna.data = new Date();
   			tempoUrna.codUrna = codUrna;
   			tempoUrna.tempoTotal = 30;
   			tempoUrna.save();  	
   	   	    pedidoTempo = true;
   	   		renderText("primeiro pedido de tempo, Agarde um momento!");
   		}
   		else 
   		{
   			tempUrna.tempoTotal += 30;
   			tempUrna.save();  
   			pedidoTempo = true;
   			renderText("Segundo pedido de tempo, Agarde um momento!");
   		}	
    }

     public static void addTempo(){

    	 if(pedidoTempo == true) {
    		  pedidoTempo = false; 
    		  renderJSON(true);  
    	  }
    	  renderJSON(false);
      }    
}
