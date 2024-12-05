package category;

import org.bukkit.Material;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ShopCategoryManager {

    private final Map<ShopCategory, Set<Material>> categoryMappings = new HashMap<>();

    public void initializeCategories() {
        // Initialize each category with its items
        for (ShopCategory category : ShopCategory.values()) {
            categoryMappings.put(category, new HashSet<>());
        }

        // BUILDING_BLOCKS
        addToCategory(ShopCategory.BUILDING_BLOCKS,
            Material.OAK_LOG
        );

        // COLORED_BLOCKS
        addToCategory(ShopCategory.COLORED_BLOCKS,
            Material.WHITE_WOOL
        );

        // NATURAL_BLOCKS
        addToCategory(ShopCategory.NATURAL_BLOCKS,
                Material.GRASS_BLOCK
        );

        // FUNCTIONAL_BLOCKS
        addToCategory(ShopCategory.FUNCTIONAL_BLOCKS,
            Material.TORCH
        );

        // REDSTONE_BLOCKS
        addToCategory(ShopCategory.REDSTONE_BLOCKS,
            Material.REDSTONE
        );

        // TOOLS_AND_UTILITIES
        addToCategory(ShopCategory.TOOLS_AND_UTILITIES,
            Material.WOODEN_SHOVEL
        );

        // COMBAT
        addToCategory(ShopCategory.COMBAT,
            Material.WOODEN_SWORD
        );

        // FOOD_AND_DRINKS
        addToCategory(ShopCategory.FOOD_AND_DRINKS,
            Material.APPLE
        );

        // INGREDIENTS
        addToCategory(ShopCategory.INGREDIENTS,
            Material.COAL
        );

    }

    /**
     * Adds materials to a specific shop category
     * @param category The shop category to add items to
     * @param materials The materials to add to the category
     */
    private void addToCategory(ShopCategory category, Material... materials) {
        categoryMappings.computeIfAbsent(category, k -> new HashSet<>()).addAll(Arrays.asList(materials));
    }

    /**
     * Adds materials to a category based on a regex pattern matching their names
     * @param category The shop category to add items to
     * @param pattern The regex pattern to match material names against
     */
    private void addToCategory(ShopCategory category, Pattern pattern){
        // Filter all materials whose names match the given pattern
        Set<Material> matchingItems = Arrays.stream(Material.values())
                .filter(material -> !material.name().contains("LEGACY") && pattern.matcher(material.name()).matches())
                .collect(Collectors.toSet());

        // Add all matching materials to the category, creating a new set if needed
        categoryMappings.computeIfAbsent(category, k -> new HashSet<>()).addAll(matchingItems);
    }

    /**
     * Gets all items in a specific shop category
     * @param category The shop category to get items from
     * @return A new Set containing all Materials in the specified category
     */
    public Set<Material> getItemsInCategory(ShopCategory category) {
        return new HashSet<>(categoryMappings.get(category));
    }

    /**
     * Gets all shop categories that contain a specific material
     * @param material The material to find categories for
     * @return A Set of ShopCategory containing all categories that include the specified material
     */
    public Set<ShopCategory> getCategoriesForItem(Material material) {
        Set<ShopCategory> categories = new HashSet<>();
        // Iterate through each category and its associated materials
        for (Map.Entry<ShopCategory, Set<Material>> entry : categoryMappings.entrySet()) {
            // If the category contains the specified material, add it to the result set
            if (entry.getValue().contains(material)) {
                categories.add(entry.getKey());
            }
        }
        return categories;
    }

    /**
     * Récupère tous les items qui n'ont pas été catégorisés
     * @return Set de Material non catégorisés
     */
    public Set<Material> getUncategorizedItems() {
        // Récupère tous les items catégorisés
        Set<Material> categorizedItems = new HashSet<>();
        categoryMappings.values().forEach(categorizedItems::addAll);

        // Récupère tous les items existants
        Set<Material> allItems = new HashSet<>(Arrays.asList(Material.values()));

        // Retire les items catégorisés
        allItems.removeAll(categorizedItems);

        //TODO ajouter des items ignorés (LEGACY, BEDROCK, etc)

        return allItems;
    }

    /**
     * Méthode utilitaire pour afficher les items non catégorisés de manière formatée
     */
    public void printUncategorizedItems() {
        Set<Material> uncategorized = getUncategorizedItems();
        System.out.println("=== Items non catégorisés (" + uncategorized.size() + ") ===");

        // Trie les items par nom pour une meilleure lisibilité
        List<Material> sortedItems = new ArrayList<>(uncategorized);
        sortedItems.sort(Comparator.comparing(Enum::name));

        System.out.println("================================");
    }

    /**
     * Vérifie si un item est catégorisé
     */
    public boolean isItemCategorized(Material material) {
        return categoryMappings.values().stream()
                .anyMatch(categoryItems -> categoryItems.contains(material));
    }
    
}
