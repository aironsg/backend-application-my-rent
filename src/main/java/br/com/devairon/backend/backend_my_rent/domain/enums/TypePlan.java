package br.com.devairon.backend.backend_my_rent.domain.enums;

public enum TypePlan {

    PLAN_TEST("plan_test"),
    PLAN_BASIC("plan_basic"),
    PLAN_INTERMEDIATE("plan_intermediate"),
    PLAN_PREMIUM("plan_premium");

    private String typePlan;

    TypePlan(String typePlan) {
        this.typePlan = typePlan;
    }

    public String getTypePlan() {
        return typePlan;
    }
}
