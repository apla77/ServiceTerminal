package models;

import javax.persistence.Entity;

import play.db.jpa.Model;

@Entity
public class IpTerminal extends Model{
	
	public String ipTerminal;
	public Long idSecao;
 
}
