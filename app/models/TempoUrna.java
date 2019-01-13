package models;

import java.util.Date;

import javax.persistence.Entity;

import com.mysql.fabric.xmlrpc.base.Data;

import play.db.jpa.Model;

@Entity
public class TempoUrna extends Model{
	
	public Date data; 
	public Long codUrna;
	public int tempoTotal;

}
