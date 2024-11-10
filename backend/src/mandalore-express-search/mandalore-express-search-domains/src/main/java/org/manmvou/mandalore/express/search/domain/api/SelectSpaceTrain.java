package org.manmvou.mandalore.express.search.domain.api;

import org.manmvou.mandalore.express.search.domain.Search;

import java.util.UUID;

public interface SelectSpaceTrain {

    default SelectFare havingTheNumber(String spaceTrainNumber) {
        return selectSpaceTrain(spaceTrainNumber);
    }

    Search selectFareOfSpaceTrainInSearch(String spaceTrainNumber, UUID fareId, UUID searchId, boolean resetSelection);

    private SelectFare selectSpaceTrain(String number) {
        return fareId -> selectFare(number, fareId);
    }

    private InSearchSelection selectFare(String number, UUID fareId) {
        return searchId -> byResettingSelection(number, fareId, searchId);
    }

    private ByResettingSelection byResettingSelection(String number, UUID fareId, UUID searchId) {
        return resetSelection -> selectFareOfSpaceTrainInSearch(number, fareId, searchId, resetSelection);
    }

    // Type aliases are not needed in Java, but you can define functional interfaces if needed
    @FunctionalInterface
    interface SelectFare {
        InSearchSelection invoke(UUID fareId);
    }

    @FunctionalInterface
    interface InSearchSelection {
        ByResettingSelection invoke(UUID searchId);
    }

    @FunctionalInterface
    interface ByResettingSelection {
        Search invoke(boolean resetSelection);
    }
}

// Extension methods can be implemented as static methods in a utility class
class SelectFareExtensions {
    public static SelectSpaceTrain.InSearchSelection withTheFare(SelectSpaceTrain.SelectFare selectFare, UUID fareId) {
        return selectFare.invoke(fareId);
    }

    public static SelectSpaceTrain.ByResettingSelection inSearch(SelectSpaceTrain.InSearchSelection inSearchSelection, UUID searchId) {
        return inSearchSelection.invoke(searchId);
    }

    public static Search byResettingTheSelection(SelectSpaceTrain.ByResettingSelection byResettingSelection, boolean resetSelection) {
        return byResettingSelection.invoke(resetSelection);
    }
}