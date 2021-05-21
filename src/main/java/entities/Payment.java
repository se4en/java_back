package entities;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table (name = "payments")
public class Payment {

    @Column(name = "paym_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "work_id")
    private Worker worker;

    private String type;
    private java.sql.Timestamp date_time;
    private long amount;

    public Payment() {}

    public Payment(Worker worker, String type, Timestamp date_time, int amount) {
        this.worker = worker;
        this.type = type;
        this.date_time = date_time;
        this.amount = amount;
    }

    public long getId() {
        return id;
    }

    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Timestamp getDate_time() {
        return date_time;
    }

    public void setDate_time(Timestamp date_time) {
        this.date_time = date_time;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Payment payment = (Payment) o;
        return id == payment.id;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", worker sername=" + worker.getSername() +
                ", type='" + type + '\'' +
                ", date_time=" + date_time +
                ", amount=" + amount +
                '}';
    }
}
