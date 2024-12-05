package category;

public enum ShopCategory {

    BUILDING_BLOCKS("Blocks de construction"),
    COLORED_BLOCKS(""),
    NATURAL_BLOCKS(""),
    FUNCTIONAL_BLOCKS(""),
    REDSTONE_BLOCKS(""),
    TOOLS_AND_UTILITIES(""),
    COMBAT(""),
    FOOD_AND_DRINKS(""),
    INGREDIENTS("");

    private final String displayName;

    ShopCategory(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

}
