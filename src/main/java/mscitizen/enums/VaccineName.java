package mscitizen.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

public enum VaccineName {
    BCG("BCG"),
    HEPATITE_B("Hepatite B"),
    PENTAVALENTE("Pentavalente"),
    POLIOMELITE("Poliomelite"),
    PNEUMOCOCICA("Pneumocócica"),
    ROTAVIRUS("Rotavírus"),
    MENINGOCOCICA_C("Meningocócica C"),
    INFLUENZA("Influenza"),
    FEBRE_AMARELA("Febre Amarela"),
    TRIPLICE_VIRAL("Tríplice Viral"),
    DTP("DTP"),
    HEPATITE_A("Hepatite A"),
    TETRA_VIRAL("Tetra Viral"),
    VARICELA_ATENUADA("Varicela Atenuada"),
    HPV("HPV"),
    DT("DT"),
    DTPA("DTPA"),
    PFIZER("Pfizer"),
    CORONAVAC("Coronavac"),
    JANSSEN("Janssen"),
    ASTRAZENECA("Astrazeneca");

    private String value;

    VaccineName(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return this.value;
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static VaccineName forValues(@JsonProperty("nome_vacina") String value) {
        for (VaccineName vaccineName : VaccineName.values()) {
            if (vaccineName.value.equalsIgnoreCase(value)) {
                return vaccineName;
            }
        }
        return null;
    }
}
