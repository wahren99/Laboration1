package vehicles;

import vehicles.models.CarTransport;
import vehicles.models.Saab95;
import vehicles.models.Scania;
import vehicles.models.Volvo240;

/**
 * Represents an operation that may be performed on different vehicles.
 *
 * This is open/closed as fuck.
 *
 * @param <R> The result type of the operation.
 */
public interface VehicleVisitor<R> {
    /**
     * Does the thing on a Volvo.
     *
     * @param volvo240 The vehicle in question.
     * @return The result of doing the thing.
     */
    R visit(Volvo240 volvo240);

    /**
     * Does the thing on a Saab.
     *
     * @param saab95 The vehicle in question.
     * @return The result of doing the thing.
     */
    R visit(Saab95 saab95);

    /**
     * Does the thing on a vehicles.models.Scania.
     *
     * @param scania The vehicle in question.
     * @return The result of doing the thing.
     */
    R visit(Scania scania);

    /**
     * Does the thing on a car transport.
     *
     * @param carTransport The vehicle in question.
     * @param <T> The type of the things the transport carries.
     * @return The result of doing the thing.
     */
    <T extends Vehicle> R visit(CarTransport<T> carTransport);
}
