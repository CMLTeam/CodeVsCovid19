package com.cmlteam.codevscovid19;

import com.cmlteam.codevscovid19.models.Customer;
import com.cmlteam.codevscovid19.models.Doctor;
import com.cmlteam.codevscovid19.models.Slot;
import com.cmlteam.codevscovid19.models.Target;
import com.cmlteam.codevscovid19.repo.CustomerRepository;
import com.cmlteam.codevscovid19.repo.DoctorRepository;
import com.cmlteam.codevscovid19.repo.SlotRepository;
import com.cmlteam.codevscovid19.repo.TargetRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

@Component
public class PopulateService {

  private final CustomerRepository customerRepository;
  private final DoctorRepository doctorRepository;
  private final SlotRepository slotRepository;
  private final TargetRepository targetRepository;

  public PopulateService(CustomerRepository customerRepository, DoctorRepository doctorRepository, SlotRepository slotRepository, TargetRepository targetRepository) {
    this.customerRepository = customerRepository;
    this.doctorRepository = doctorRepository;
    this.slotRepository = slotRepository;
    this.targetRepository = targetRepository;
  }

  void populateTables() {
    Customer customer2wifeOfMainCustomer = new Customer(
      1002,
      "+123457654543",
      "ID-75645",
      "Jennifer Doe",
      1000,
      Customer.CustomerStatus.normal,
      "Prenzlauer Allee 248-251, 10405 Berlin, Germany",
      "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQGbA4q_zwzLq8uWFZnLBGGeUY2KVAk0IndY4q3tp_28jwgj994",
      Arrays.asList(1001),
      new ArrayList<>()
    );

    customerRepository.create(customer2wifeOfMainCustomer);

    Customer customer1main = new Customer(
      1001,
      "+123567655656",
      "ID-53453",
      "John Doe",
      960,
      Customer.CustomerStatus.normal,
      "Prenzlauer Allee 248-251, 10405 Berlin, Germany",
      "https://pickaface.net/gallery/avatar/Garret22785730d3a8d5525.png",
      new ArrayList<>(Arrays.asList(1002)),
      new ArrayList<>()
    );

    Customer customer2main = new Customer(
      1003,
      "+123567653895696",
      "ID-5344354",
      "John Doe Younger",
      250,
      Customer.CustomerStatus.ill,
      "Prenzlauer Allee 248-251, 10405 Berlin, Germany",
      "https://pickaface.net/gallery/avatar/unr_mmem_200330_0404_2o3l89n.png",
      new ArrayList<>(Arrays.asList(1002)),
      new ArrayList<>()
    );

    Customer customer3main = new Customer(
      546,
      "555-55-55",
      "DI-25645",
      "John Dou Micklovich",
      0,
      Customer.CustomerStatus.ill,
      "Third planet from Sun",
      "/546_profile_photo.jpg",
      new ArrayList<>(),
      new ArrayList<>()
    );

    Customer customer4main = new Customer(
      547,
      "555-34-55",
      "DI-25646",
      "Johna Dou Silvestrovna",
      356,
      Customer.CustomerStatus.analysis,
      "Third planet from Sun",
      null,
      new ArrayList<>(),
      new ArrayList<>()
    );
    Customer customer5main = new Customer(
      85,
      "356-34-55",
      "DI-25456",
      "Vsiliy Pupkin",
      1000,
      Customer.CustomerStatus.normal,
      "Ukrain, Kyiv, Main Street 345, fl. 86",
      null,
      new ArrayList<>(),
      new ArrayList<>()
    );

    customerRepository.create(customer1main);
    customerRepository.create(customer2main);
    customerRepository.create(customer3main);
    customerRepository.create(customer4main);
    customerRepository.create(customer5main);

    Doctor doctor1main = new Doctor(
      2001,
      "Dr. Michael Bale",
      new ArrayList<>(Arrays.asList(1001, 1003, 546, 547, 85))
    );

    doctorRepository.create(doctor1main);

    populateTargetsWithSlots();
  }

  private void populateTargetsWithSlots() {
    Target target1 = new Target(
      4001,
      Target.TargetType.shop,
      "Supermarket1",
      300L,
      "Prenzlauer Allee 242, 10405 Berlin, Germany",
      100.0,
      100.0,
      "9.00 AM - 9.00 PM.",
      "https://cdn.icon-icons.com/icons2/1706/PNG/512/3986701-online-shop-store-store-icon_112278.png",
      new ArrayList<>(Arrays.asList(3001, 3002, 3003))
    );

    targetRepository.create(target1);

    Slot slot1target1 = new Slot(
      3001,
      4001,
      "9.00 - 12.30 AM",
      LocalDateTime.of(2020, 5, 1, 9, 0, 0),
      LocalDateTime.of(2020, 5, 1, 12, 30, 0),
      7
    );

    Slot slot2target1 = new Slot(
      3002,
      4001,
      "12.30 - 16.00 AM",
      LocalDateTime.of(2020, 5, 1, 12, 30, 0),
      LocalDateTime.of(2020, 5, 1, 16, 0, 0),
      34
    );

    Slot slot3target1 = new Slot(
      3003,
      4001,
      "16.00 - 21.00 AM",
      LocalDateTime.of(2020, 5, 1, 16, 0, 0),
      LocalDateTime.of(2020, 5, 1, 21, 0, 0),
      56
    );

    slotRepository.create(slot1target1);
    slotRepository.create(slot2target1);
    slotRepository.create(slot3target1);

    Target target2 = new Target(
      4002,
      Target.TargetType.shop,
      "Supermarket1",
      300L,
      "Brunnenstraße 197-198, 10119 Berlin, Germany",
      200.0,
      200.0,
      "8.00 AM - 9.00 PM.",
      "https://cdn.icon-icons.com/icons2/1706/PNG/512/3986701-online-shop-store-store-icon_112278.png",
      new ArrayList<>(Arrays.asList(3004, 3005, 3006))
    );

    targetRepository.create(target2);

    Slot slot1target2 = new Slot(
      3004,
      4002,
      "8.00 - 13.00 AM",
      LocalDateTime.of(2020, 5, 1, 8, 0, 0),
      LocalDateTime.of(2020, 5, 1, 13, 0, 0),
      12
    );

    Slot slot2target2 = new Slot(
      3005,
      4002,
      "13.00 - 17.30 AM",
      LocalDateTime.of(2020, 5, 1, 13, 0, 0),
      LocalDateTime.of(2020, 5, 1, 17, 30, 0),
      45
    );

    Slot slot3target2 = new Slot(
      3006,
      4002,
      "17.30 - 21.00 AM",
      LocalDateTime.of(2020, 5, 1, 17, 30, 0),
      LocalDateTime.of(2020, 5, 1, 21, 0, 0),
      24
    );

    slotRepository.create(slot1target2);
    slotRepository.create(slot2target2);
    slotRepository.create(slot3target2);


  }
}
