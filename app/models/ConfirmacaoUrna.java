package models;

import javax.persistence.Entity;

import play.db.jpa.Model;

@Entity
public class ConfirmacaoUrna extends Model{
	public boolean confirma; 
}
