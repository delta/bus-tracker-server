package edu.nitt.delta.bustracker;


import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import edu.nitt.delta.bustracker.classes.VehicleType;
import edu.nitt.delta.bustracker.model.Driver;
import edu.nitt.delta.bustracker.model.Vehicle;
import edu.nitt.delta.bustracker.repository.DriverRepository;
import edu.nitt.delta.bustracker.repository.VehicleRepository;

@SpringBootApplication
public class BusTrackerServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BusTrackerServerApplication.class, args);
	}


	/**
	 * 
	 * Making some test data for db
	 * 
	 * @param driverRepository
	 * @param mongoTemplate
	 * @return
	 */

	@Bean
	CommandLineRunner driverRunner(DriverRepository driverRepository, MongoTemplate mongoTemplate) {
		
		return args -> {

			for (int i = 0; i < 10; ++i) {

				Driver driver = new Driver(
					"firstName" + Integer.toString(i),
					"LastName" + Integer.toString(i),
					"Password" + Integer.toString(i),
					i,
					"email.com" + Integer.toString(i)
				); 


				Query query = new Query();
				query.addCriteria(Criteria.where("email").is(driver.getEmail()));

				List<Driver> drivers = mongoTemplate.find(query, Driver.class);

				if (drivers.size() > 1) {
					throw new IllegalStateException("Found other users with email: " + driver.getEmail());
				} else if (drivers.size() == 1) {
					System.out.println("found other user with email: " + driver.getEmail());
				} else {
					System.out.println("Inserting user");
					driverRepository.insert(driver);
				}



			}
		};
	}

	@Bean
	CommandLineRunner vehicalRunner(VehicleRepository vehicleRepository, MongoTemplate mongoTemplate) {
		
		return args -> {

			for (int i = 0; i < 10; ++i) {

				Vehicle vehicle = new Vehicle(
					VehicleType.bus,
					5 + i,
					"ABCD" + Integer.toString(i*i)
				);


				Query query = new Query();
				query.addCriteria(Criteria.where("vehicleNumber").is(vehicle.getVehicleNumber()));

				List<Vehicle> vehicles = mongoTemplate.find(query, Vehicle.class);

				if (vehicles.size() > 1) {
					throw new IllegalStateException("Found other vehicle with vehicleNumber: " + vehicle.getVehicleNumber());
				} else if (vehicles.size() == 1) {
					System.out.println("found other user with email: " + vehicle.getVehicleNumber());
				} else {
					System.out.println("Inserting vehicle");
					vehicleRepository.insert(vehicle);
				}



			}
		};
	}

}
