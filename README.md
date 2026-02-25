# TellyGUIs (PagesAPI)
- Created by TellyMC

## How to Install

1) Add the Repository within the Pom.xml
```xml
<repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
</repository>
```

2) Add the Dependency within the Pom.xml
```xml
<dependency>
    <groupId>com.github.tellymc</groupId>
    <artifactId>TellyGUIs</artifactId>
    <version>1.1.0</version>
</dependency>
```
3) Reload Maven

## How to Use
1) Add the PagesAPI in the main class
```java
public final class PagesTest extends JavaPlugin {
    
    // Make a private instance of PagesAPI
    private PagesAPI pagesAPI;

    @Override
    public void onEnable() {

        // Get the actual API
        pagesAPI = new PagesAPI(this);
    }

    // Make it accessible from other classes
    public PagesAPI getPagesAPI() {
        return pagesAPI;
    }
}
```

2) Creating a page
```java
public class TestCMD implements CommandExecutor {
    
    private final PagesTest pagesTest;
    
    public TestCMD(PagesTest pagesTest) {
        this.pagesTest = pagesTest;
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        
        if (!(sender instanceof Player)) return false;
        Player player = (Player) sender;

        // Gets the PagesAPI from the Main Class
        PagesAPI pagesAPI = pagesTest.getPagesAPI();
    
        // Creates the actual page with the parameters of size and title of the inventory
        Page page = pagesAPI.createPage(6, "Test Page"); 

        // Open it afterward
        page.open(player);

        return true;
    }
}
```
3) Adding an item to the GUI
```java
public class TestCMD implements CommandExecutor {
    
    private final PagesTest pagesTest;
    
    public TestCMD(PagesTest pagesTest) {
        this.pagesTest = pagesTest;
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        
        if (!(sender instanceof Player)) return false;
        Player player = (Player) sender;

        // Gets the PagesAPI from the Main Class
        PagesAPI pagesAPI = pagesTest.getPagesAPI();
    
        // Creates the actual page with the parameters of size and title of the inventory
        Page page = pagesAPI.createPage(6, "Test Page");

        // Creates an item and sets it within the GUI
        PageItem item = page1.setSlot(4, new ItemStack(Material.BARRIER), "Exit GUI");

        // Open it afterward
        page.open(player);

        return true;
    }
}
```

## Examples
1) Adding a Click-Action to an Item
```java
public class TestCMD implements CommandExecutor {
    
    private final PagesTest pagesTest;
    
    public TestCMD(PagesTest pagesTest) {
        this.pagesTest = pagesTest;
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        
        if (!(sender instanceof Player)) return false;
        Player player = (Player) sender;

        // Gets the PagesAPI from the Main Class
        PagesAPI pagesAPI = pagesTest.getPagesAPI();
    
        // Creates the actual page with the parameters of size and title of the inventory
        Page page = pagesAPI.createPage(6, "Test Page");

        // Creates an item and sets it within the GUI
        PageItem item = page1.setSlot(4, new ItemStack(Material.BARRIER), "Exit GUI");

        // Closes the inventory on the player
        item.setClickAction(event -> {
            player.closeInventory();
        });

        // Open it afterward
        page.open(player);

        return true;
    }
}
```

2) Using .column(), .row(), .outline(), and .fill()
```java
public class TestCMD implements CommandExecutor {
    
    private final PagesTest pagesTest;
    
    public TestCMD(PagesTest pagesTest) {
        this.pagesTest = pagesTest;
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        
        if (!(sender instanceof Player)) return false;
        Player player = (Player) sender;

        // Gets the PagesAPI from the Main Class
        PagesAPI pagesAPI = pagesTest.getPagesAPI();
    
        // Creates the actual page with the parameters of size and title of the inventory
        Page page = pagesAPI.createPage(6, "Test Page");
        
        // Fill the page first
        page.fill(new ItemStack(Material.STAINED_GLASS_PANE));
        
        // Fill the outer border of the GUI
        page.outline(new ItemStack(Material.DIAMOND_BLOCK));
        
        // Add a column in the middle
        page.column(4, new ItemStack(Material.WOOL));
        
        // Add a column in row 3 (base 0 indexing)
        page.row(2, new ItemStack(Material.WOOL));

        // Open it afterward
        page.open(player);

        return true;
    }
}
```

3) Multi-Page GUI
```java
public class TestCMD implements CommandExecutor {
    
    private final PagesTest pagesTest;
    
    public TestCMD(PagesTest pagesTest) {
        this.pagesTest = pagesTest;
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        
        if (!(sender instanceof Player)) return false;
        Player player = (Player) sender;

        // Gets the PagesAPI from the Main Class
        PagesAPI pagesAPI = pagesTest.getPagesAPI();
        
        // Creates the two pages that it will go between
        Page page1 = pagesAPI.createPage(6, "First Page");
        Page page2 = pagesAPI.createPage(6, "Second Page");
        
        // Creates an item for the first GUI for going to the next page (page2)
        PageItem item1 = page1.setSlot(8, new ItemStack(Material.ARROW), "Next Page");
        
        // Sets the next page to page2
        page1.setNextPage(page2);
        
        // Makes the click event open the next page
        item1.setClickAction(event -> {
            page1.openNextPage(player);
        });
        
        // Creates an item for the second GUI for going to the last page (page1)
        PageItem item2 = page2.setSlot(0, new ItemStack(Material.ARROW), "Last Page");
        
        // Sets the last page to page1
        page2.setLastPage(page1);
        
        // Makes the click event open the last page
        item2.setClickAction(event -> {
            page2.openLastPage(player);
        });
        
        // Open it afterward
        page.open(player);

        return true;
    }
}
```