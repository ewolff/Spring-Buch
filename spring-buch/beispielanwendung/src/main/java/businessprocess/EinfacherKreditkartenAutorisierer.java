package businessprocess;

import java.io.Serializable;

public class EinfacherKreditkartenAutorisierer implements
        KreditkartenAutorisierer, Serializable {

    private boolean authorisierung = true;

    public EinfacherKreditkartenAutorisierer() {
        super();
    }

    public EinfacherKreditkartenAutorisierer(boolean authorisierung) {
        super();
        this.authorisierung = authorisierung;
    }

    public boolean belasten(int nummer, double betrag) {
        return authorisierung;
    }

    public boolean isAuthorisierung() {
        return authorisierung;
    }

    public void setAuthorisierung(boolean authorisierung) {
        this.authorisierung = authorisierung;
    }

}
