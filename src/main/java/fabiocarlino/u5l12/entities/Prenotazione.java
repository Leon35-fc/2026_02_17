package fabiocarlino.u5l12.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "prenotazioni")
public class Prenotazione {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dataRichiesta;

    @Column(nullable = false)
    private String note;

    @ManyToOne
    @JoinColumn(name = "viaggio_id", nullable = false)
    private Viaggio viaggioId;

    @ManyToOne
    @JoinColumn(name = "dipendente_id", nullable = false)
    private Dipendente dipendenteId;

    public Prenotazione() {
    }

    public Prenotazione(String note, Viaggio viaggioId, Dipendente dipendenteId) {
        this.dataRichiesta = LocalDate.now();
        this.note = note;
        this.viaggioId = viaggioId;
        this.dipendenteId = dipendenteId;
    }

    public UUID getId() {
        return id;
    }

    public LocalDate getDataRichiesta() {
        return dataRichiesta;
    }

    public void setDataRichiesta(LocalDate dataRichiesta) {
        this.dataRichiesta = dataRichiesta;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Viaggio getViaggioId() {
        return viaggioId;
    }

    public void setViaggioId(Viaggio viaggioId) {
        this.viaggioId = viaggioId;
    }

    public Dipendente getDipendenteId() {
        return dipendenteId;
    }

    public void setDipendenteId(Dipendente dipendenteId) {
        this.dipendenteId = dipendenteId;
    }

    @Override
    public String toString() {
        return "Prenotazione{" +
                "id=" + id +
                ", dataRichiesta=" + dataRichiesta +
                ", note='" + note + '\'' +
                ", viaggioId=" + viaggioId +
                ", dipendenteId=" + dipendenteId +
                '}';
    }
}


