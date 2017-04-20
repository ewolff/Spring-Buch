package dao;

import java.util.List;

import businessobjects.Bestellung;

public interface IBestellungDAO {

    Bestellung save(Bestellung bestellung);

    void deleteByIDKunde(int id_kunde);

    List<Bestellung> getByIDKunde(int id_kunde);

}