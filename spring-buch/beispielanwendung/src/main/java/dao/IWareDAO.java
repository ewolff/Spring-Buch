package dao;

import java.util.List;

import businessobjects.Ware;

public interface IWareDAO {

    List<Ware> getAll();
    
    List<Ware> getByBezeichnung(String bezeichnung);

    Ware save(Ware ware);

    void deleteByBezeichnung(String bezeichnung);

    Ware getByID(int id);

    void deleteByID(int id);

    void update(Ware ware);

}
