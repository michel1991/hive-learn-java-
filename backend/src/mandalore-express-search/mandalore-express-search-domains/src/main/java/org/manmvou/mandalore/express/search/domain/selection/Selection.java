package org.manmvou.mandalore.express.search.domain.selection;

import org.manmvou.mandalore.express.search.domain.spacetrain.Bound;
import org.manmvou.mandalore.express.search.domain.spacetrain.SpaceTrain;
import org.manmvou.mandalore.express.search.domain.spacetrain.fare.Price;

import java.util.*;
import java.util.stream.Collectors;

public class Selection implements Map<Bound, SelectedSpaceTrain> {

    private final Map<Bound, SelectedSpaceTrain> selectedSpaceTrainsByBound;

    public Selection(Map<Bound, SelectedSpaceTrain> selectedSpaceTrainsByBound) {
        this.selectedSpaceTrainsByBound = selectedSpaceTrainsByBound;
    }

    public Selection() {
        this.selectedSpaceTrainsByBound = Collections.emptyMap();
    }

    public Set<Bound> getBounds() {
        return selectedSpaceTrainsByBound.keySet();
    }

    public Set<SelectedSpaceTrain> getSpaceTrains() {
        return Set.copyOf(selectedSpaceTrainsByBound.values());
    }

    public Set<Entry<Bound, SelectedSpaceTrain>> getSpaceTrainsByBound() {
        return selectedSpaceTrainsByBound.entrySet();
    }

    public Price getTotalPrice() {
        return selectedSpaceTrainsByBound.values().stream()
                .map(SelectedSpaceTrain::getPrice)
                .reduce(Price::plus)
                .orElse(null);
    }

    public Selection selectSpaceTrainWithFare(SpaceTrain spaceTrain, UUID fareId, Price price) {
        Map<Bound, SelectedSpaceTrain> newSelectedSpaceTrains = new HashMap<>(selectedSpaceTrainsByBound);
        newSelectedSpaceTrains.put(
                spaceTrain.getBound(),
                new SelectedSpaceTrain(spaceTrain.getNumber(), fareId, price)
        );
        return new Selection(newSelectedSpaceTrains);
    }

    public boolean hasASelectionFor(Bound bound) {
        return selectedSpaceTrainsByBound.containsKey(bound);
    }

    @Override
    public int size() {
        return selectedSpaceTrainsByBound.size();
    }

    @Override
    public boolean isEmpty() {
        return selectedSpaceTrainsByBound.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return selectedSpaceTrainsByBound.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return selectedSpaceTrainsByBound.containsValue(value);
    }

    @Override
    public SelectedSpaceTrain get(Object key) {
        return selectedSpaceTrainsByBound.get(key);
    }

    @Override
    public SelectedSpaceTrain put(Bound key, SelectedSpaceTrain value) {
        throw new UnsupportedOperationException("Selection is immutable");
    }

    @Override
    public SelectedSpaceTrain remove(Object key) {
        throw new UnsupportedOperationException("Selection is immutable");
    }

    @Override
    public void putAll(Map<? extends Bound, ? extends SelectedSpaceTrain> m) {
        throw new UnsupportedOperationException("Selection is immutable");
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Selection is immutable");
    }

    @Override
    public Set<Bound> keySet() {
        return selectedSpaceTrainsByBound.keySet();
    }

    @Override
    public Collection<SelectedSpaceTrain> values() {
        return selectedSpaceTrainsByBound.values();
    }

    @Override
    public Set<Entry<Bound, SelectedSpaceTrain>> entrySet() {
        return selectedSpaceTrainsByBound.entrySet();
    }

    public SelectedSpaceTrain getSelectedSpaceTrain(Bound bound) {
        return this.get(bound);
    }
}
