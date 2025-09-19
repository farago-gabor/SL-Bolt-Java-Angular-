package hu.projekt.bolt.mapper;

import hu.projekt.bolt.dto.TevekenysegDTO;
import hu.projekt.bolt.dto.TevekenysegNaploDTO;
import hu.projekt.bolt.model.Tevekenyseg;
import hu.projekt.bolt.model.TevekenysegGyakorisag;
import hu.projekt.bolt.model.TevekenysegNaplo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;


import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Mapper(componentModel = "spring")
public interface TevekenysegMapper {

    TevekenysegNaploDTO TevekenysegNaploToDTO(TevekenysegNaplo tevekenysegNaplo);


    /* AZ ALÁBBI DOLGOKAT KELL MEGVALÓSÍTANI:

        - A napok és időpontok lehetnek NULL, ha a gyakorsiág MINDIG-re van állítva, ekkor nem kell lekezelni
        - A tevékenységet úgy mappelje a TevekenysegDTO-hoz hogy a napok és időpontok egy Map-ben(kulcs - érték) tárolja el


            private String> gyakorisag;
            private Map<String, String> napokIdopontok;  // Map, ahol a napokhoz rendeljük az időpontokat (pl. "hétfő": "06:00", "kedd": "09:00")

     */

    @Mapping(source = "tevekenyseg.megnevezes", target = "megnevezes")
    @Mapping(source = "tevekenyseg.leiras", target = "leiras")
    @Mapping(source = "tevekenysegGyakorisag.gyakorisag", target = "gyakorisag")
    @Mapping(source = "tevekenysegGyakorisag.kezdoDatum", target = "kezdoDatum")
    @Mapping(source = "tevekenysegGyakorisag", target = "napokIdopontok", qualifiedByName = "mapNapokIdopontok")
    TevekenysegDTO toTevekenysegDTO(Tevekenyseg tevekenyseg, TevekenysegGyakorisag tevekenysegGyakorisag);

    @Named("mapNapokIdopontok")
    default Map<String, String> mapNapokIdopontok(TevekenysegGyakorisag gyakorisag) {
        Map<String, String> napokIdopontok = new HashMap<>();

        // Ha gyakoriság MINDIG, akkor ne kezeljünk napokat és időpontokat
        if (gyakorisag.getGyakorisag() == TevekenysegGyakorisag.Gyakorisag.MINDIG) {
            return napokIdopontok;  // Visszaadjuk az üres map-et
        }

        // Más esetekben figyeljük a napokat és időpontokat
        for (String nap : gyakorisag.getNapok()) {
            napokIdopontok.put(nap, gyakorisag.getIdoPont());
        }

        return napokIdopontok;
    }
}
