package axis;

import businessobjects.Bestellung;
import dao.IBestellungDAO;

public interface IAxisBestellungDAO extends IBestellungDAO {

   public Bestellung[] getByIDKundeAsArray(int id_kunde);
    
}
