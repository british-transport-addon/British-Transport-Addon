package org.eu.awesomekalin.jta.platform.entity.vehicle;

import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import org.eu.awesomekalin.jta.mod.entity.vehicle.Door;
import org.eu.awesomekalin.jta.mod.entity.vehicle.Seat;
import org.mtr.mapping.holder.EntityType;
import org.mtr.mapping.holder.World;
import org.mtr.mapping.mapper.EntityExtension;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractVehicle extends EntityExtension {

    private final List<Seat> seats;
    private final List<Door> doors;
    private static final TrackedData<Byte> VEHICLE_FLAGS = DataTracker.registerData(AbstractVehicle.class, TrackedDataHandlerRegistry.BYTE);;
    private static final int ENGINE_FLAG = 2;
    private static final int HEADLIGHTS_FLAG = 4;
    private static final int BLINKER_LEFT_FLAG = 8;
    private static final int BLINKER_RIGHT_FLAG = 16;
    // set both for hazard lights.


    public AbstractVehicle(EntityType<?> type, World world) {
        super(type, world);
        this.seats = new ArrayList<>();
        this.doors = new ArrayList<>();
    }

    @Override
    protected void initDataTracker2() {
        this.dataTracker.startTracking(VEHICLE_FLAGS, (byte)0);
    }

    protected boolean getVehicleFlag(int bitmask) {
        return ((Byte)this.dataTracker.get(VEHICLE_FLAGS) & bitmask) != 0;
    }

    protected void setVehicleFlag(int bitmask, boolean flag) {
        byte b = (Byte)this.dataTracker.get(VEHICLE_FLAGS);
        if (flag) {
            this.dataTracker.set(VEHICLE_FLAGS, (byte)(b | bitmask));
        } else {
            this.dataTracker.set(VEHICLE_FLAGS, (byte)(b & ~bitmask));
        }

    }

    // Method to add a seat
    public void addSeat(double x, double y, double z) {
        seats.add(new Seat(x, y, z));
    }

    // Method to add a door
    public void addDoor(double x, double y, double z, double width, double height) {
        doors.add(new Door(x, y, z, width, height));
    }

    // Method to get the list of seats
    public List<Seat> getSeats() {
        return seats;
    }

    // Method to get the list of doors
    public List<Door> getDoors() {
        return doors;
    }

    // Override this in subclasses to define seat and door positions
    protected abstract void setupSeatsAndDoors();
}
