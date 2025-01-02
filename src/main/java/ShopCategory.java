import org.bukkit.Material;

public enum ShopCategory {

    BUILDING_BLOCKS("Blocs de construction", Material.BRICKS),
    COLORED_BLOCKS("Blocs colorés", Material.CYAN_WOOL),
    NATURAL_BLOCKS("Blocs naturels", Material.GRASS_BLOCK),
    FUNCTIONAL_BLOCKS("Blocs fonctionnels", Material.OAK_SIGN),
    REDSTONE_BLOCKS("Redstone", Material.REDSTONE),
    TOOLS_AND_UTILITIES("Outils et utilitaires", Material.DIAMOND_PICKAXE),
    COMBAT("Combat", Material.NETHERITE_SWORD),
    FOOD_AND_DRINKS("Nourriture et boissons", Material.GOLDEN_APPLE),
    INGREDIENTS("Ingrédients", Material.IRON_INGOT);
    //TODO dernière catégorie à récupérer via getUncategorizedItems() de la class ShopCategoryManager

    private final String displayName;
    private final Material displayMaterial;

    ShopCategory(String displayName, Material displayMaterial) {
        this.displayName = displayName;
        this.displayMaterial = displayMaterial;
    }

    public String getDisplayName() {
        return displayName;
    }

    public Material getDisplayMaterial() {
        return displayMaterial;
    }
}
