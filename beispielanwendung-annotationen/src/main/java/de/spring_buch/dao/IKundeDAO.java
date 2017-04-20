package de.spring_buch.dao;

import java.util.List;

import de.spring_buch.businessobjects.Kunde;


public interface IKundeDAO {

    List<Kunde> getByName(String name);

    Kunde save(Kunde kunde);

    void deleteByName(String name);

    Kunde getByID(int id);

    void deleteByID(int id);

    void update(Kunde kunde);
    
    List<Kunde> getAll();
}