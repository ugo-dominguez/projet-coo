public class EquipLegendaryItemCommand implements GameAction {
    private Player player;
    private LegendaryItem legendaryItem;
    private Game game;

    public EquipLegendaryItemCommand(Player player, LegendaryItem legendaryItem, Game game) {
        this.player = player;
        this.legendaryItem = legendaryItem;
        this.game = game;
    }

    @Override
    public void execute() {
        if (player instanceof DualClassPlayer) {
            System.out.println("\n" + ConsoleColors.ANSI_RED + "Vous possédez déjà une seconde classe !" + ConsoleColors.ANSI_RESET);
            System.out.println("Vous ne pouvez pas cumuler plus de deux classes.");
            return;
        }

        System.out.println("\n" + ConsoleColors.ANSI_PURPLE + "════════════════════════════════════════" + ConsoleColors.ANSI_RESET);
        System.out.println(ConsoleColors.ANSI_YELLOW + legendaryItem.getFlavorText() + ConsoleColors.ANSI_RESET);
        System.out.println(ConsoleColors.ANSI_PURPLE + "════════════════════════════════════════" + ConsoleColors.ANSI_RESET);
        System.out.println(ConsoleColors.ANSI_GREEN + "Vous obtenez la classe : " + legendaryItem.getGrantedClassName() + " !" + ConsoleColors.ANSI_RESET);
        
        DualClassPlayer dualClassPlayer = new DualClassPlayer(
            player, 
            legendaryItem.getGrantedClassName(), 
            legendaryItem.getGrantedAttackStrategy()
        );

        player.inventory.remove(legendaryItem);
        game.setPlayer(dualClassPlayer);
        
        System.out.println("Vous pouvez désormais choisir entre vos deux classes lors des combats !");
    }
}