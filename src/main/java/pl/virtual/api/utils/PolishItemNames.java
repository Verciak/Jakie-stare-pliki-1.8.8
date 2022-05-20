// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.utils;

import org.bukkit.Material;

public class PolishItemNames
{
    public static String getPolishName(final Material material) {
        return getPolishName(material, (short)0);
    }

    public static String getPolishName(final Material material, final short dur) {
        if (material == Material.AIR) {
            return "powietrze";
        }
        if (material == Material.ENCHANTED_GOLDEN_APPLE) {
            return "kox";
        }
        if (material == Material.EXPERIENCE_BOTTLE) {
            return "butelki expa";
        }
        if (material == Material.STONE) {
            return "kamien";
        }
        if (material == Material.GRASS) {
            return "trawa";
        }
        if (material == Material.DIRT) {
            return "dirt";
        }
        if (material == Material.COBBLESTONE) {
            return "bruk";
        }
        if (material == Material.OAK_WOOD) {
            return "drewno";
        }
        if (material == Material.OAK_SAPLING) {
            return "sadzonka";
        }
        if (material == Material.BEDROCK) {
            return "skala macierzysta";
        }
        if (material == Material.WATER) {
            return "woda";
        }
        if (material == Material.LAVA) {
            return "lava";
        }
        if (material == Material.SAND) {
            return "piasek";
        }
        if (material == Material.GRAVEL) {
            return "zwir";
        }
        if (material == Material.GOLD_ORE) {
            return "ruda zlota";
        }
        if (material == Material.IRON_ORE) {
            return "ruda zelaza";
        }
        if (material == Material.COAL_ORE) {
            return "ruda wegla";
        }
        if (material == Material.OAK_LOG) {
            return "pien";
        }
        if (material == Material.OAK_LEAVES) {
            return "liscie";
        }
        if (material == Material.SPONGE) {
            return "gabka";
        }
        if (material == Material.GLASS) {
            return "szklo";
        }
        if (material == Material.LAPIS_ORE) {
            return "ruda lazurytu";
        }
        if (material == Material.LAPIS_BLOCK) {
            return "blok lazurytu";
        }
        if (material == Material.DISPENSER) {
            return "dozownik";
        }
        if (material == Material.SANDSTONE) {
            return "piaskoiec";
        }
        if (material == Material.NOTE_BLOCK) {
            return "szafa grajaca";
        }
        if (material == Material.RED_BED) {
            return "lozko";
        }
        if (material == Material.POWERED_RAIL) {
            return "zasilane tory";
        }
        if (material == Material.DETECTOR_RAIL) {
            return "tory detekcyjne";
        }
        if (material == Material.MOVING_PISTON) {
            return "lepki tlok";
        }
        if (material == Material.COBWEB) {
            return "siec";
        }
        if (material == Material.TALL_GRASS) {
            return "dluga trawa";
        }
        if (material == Material.DEAD_BUSH) {
            return "martwy krzak";
        }
        if (material == Material.PISTON) {
            return "tlok";
        }
        if (material == Material.BROWN_MUSHROOM) {
            return "brazowy grzyb";
        }
        if (material == Material.RED_MUSHROOM) {
            return "czerwony grzyb";
        }
        if (material == Material.GOLD_BLOCK) {
            return "block zlota";
        }
        if (material == Material.IRON_BLOCK) {
            return "blok zelaza";
        }
        if (material == Material.BRICK) {
            return "cegla";
        }
        if (material == Material.TNT) {
            return "TNT";
        }
        if (material == Material.BOOKSHELF) {
            return "regal na ksiazki";
        }
        if (material == Material.MOSSY_COBBLESTONE) {
            return "zarosniety mchem bruk";
        }
        if (material == Material.OBSIDIAN) {
            return "obsydian";
        }
        if (material == Material.TORCH) {
            return "pochodnia";
        }
        if (material == Material.FIRE) {
            return "ogine";
        }
        if (material == Material.CHEST) {
            return "skrzynka";
        }
        if (material == Material.REDSTONE_WIRE) {
            return "czerwony proch";
        }
        if (material == Material.DIAMOND_ORE) {
            return "ruda diamentu";
        }
        if (material == Material.DIAMOND_BLOCK) {
            return "diamentowy blok";
        }
        if (material == Material.CRAFTING_TABLE) {
            return "stol rzemieslniczy";
        }
        if (material == Material.FURNACE) {
            return "piecyk";
        }
        if (material == Material.LADDER) {
            return "drabinka";
        }
        if (material == Material.COBBLESTONE_STAIRS) {
            return "kamienne schody";
        }
        if (material == Material.LEVER) {
            return "dzwignia";
        }
        if (material == Material.REDSTONE_ORE) {
            return "ruda czerwonego kamienia";
        }
        if (material == Material.STONE_BUTTON) {
            return "kamienny przycisk";
        }
        if (material == Material.SNOW) {
            return "snieg";
        }
        if (material == Material.ICE) {
            return "lod";
        }
        if (material == Material.SNOW_BLOCK) {
            return "block sniegu";
        }
        if (material == Material.CACTUS) {
            return "kaktus";
        }
        if (material == Material.CLAY) {
            return "glina";
        }
        if (material == Material.SUGAR_CANE) {
            return "trzcina cukrowa";
        }
        if (material == Material.JUKEBOX) {
            return "szafa grajaca";
        }
        if (material == Material.PUMPKIN) {
            return "dynia";
        }
        if (material == Material.NETHERRACK) {
            return "block netherowy";
        }
        if (material == Material.SOUL_SAND) {
            return "piasek dusz";
        }
        if (material == Material.GLOWSTONE) {
            return "jasnopyl";
        }
        if (material == Material.JACK_O_LANTERN) {
            return "smiecaca dynia";
        }
        if (material == Material.PUMPKIN_STEM) {
            return "dynia";
        }
        if (material == Material.MELON_STEM) {
            return "melon";
        }
        if (material == Material.VINE) {
            return "winorosla";
        }
        if (material == Material.BRICK_STAIRS) {
            return "ceglane schody";
        }
        if (material == Material.NETHER_BRICK) {
            return "netherowe cegly";
        }
        if (material == Material.NETHER_BRICK_STAIRS) {
            return "netherowe schody";
        }
        if (material == Material.ENCHANTING_TABLE) {
            return "stol do zaklec";
        }
        if (material == Material.BREWING_STAND) {
            return "statyw alchemiczny";
        }
        if (material == Material.CAULDRON) {
            return "kociol";
        }
        if (material == Material.END_PORTAL) {
            return "portal do kresu";
        }
        if (material == Material.END_PORTAL_FRAME) {
            return "portal do kresu";
        }
        if (material == Material.END_STONE) {
            return "kamien z kresu";
        }
        if (material == Material.DRAGON_EGG) {
            return "jajko smoka";
        }
        if (material == Material.COCOA) {
            return "kakao";
        }
        if (material == Material.SANDSTONE_STAIRS) {
            return "schody z piaskowca";
        }
        if (material == Material.EMERALD_ORE) {
            return "ruda szmaragdow";
        }
        if (material == Material.ENDER_CHEST) {
            return "skrzynia kresu";
        }
        if (material == Material.TRIPWIRE_HOOK) {
            return "zaczep na linke";
        }
        if (material == Material.TRIPWIRE) {
            return "linka";
        }
        if (material == Material.EMERALD_BLOCK) {
            return "blok szmaragdow";
        }
        if (material == Material.BEACON) {
            return "magiczna latarnia";
        }
        if (material == Material.FLOWER_POT) {
            return "doniczka";
        }
        if (material == Material.CARROT) {
            return "marchewka";
        }
        if (material == Material.POTATO) {
            return "ziemniak";
        }
        if (material == Material.PLAYER_HEAD) {
            return "glowa";
        }
        if (material == Material.ANVIL) {
            return "kowadlo";
        }
        if (material == Material.TRAPPED_CHEST) {
            return "skrzynka z polapka";
        }
        if (material == Material.DAYLIGHT_DETECTOR) {
            return "wykrywacz swaitla";
        }
        if (material == Material.REDSTONE_BLOCK) {
            return "block czerwonego kamienia";
        }
        if (material == Material.HOPPER) {
            return "zbiornik";
        }
        if (material == Material.QUARTZ_BLOCK) {
            return "blok kwarcu";
        }
        if (material == Material.QUARTZ_STAIRS) {
            return "kwarcowe schody";
        }
        if (material == Material.ACTIVATOR_RAIL) {
            return "tory aktywacyjne";
        }
        if (material == Material.DROPPER) {
            return "podajnik";
        }
        if (material == Material.ACACIA_STAIRS) {
            return "akacjowe schody";
        }
        if (material == Material.DARK_OAK_STAIRS) {
            return "schody z ciemnego drewna";
        }
        if (material == Material.HAY_BLOCK) {
            return "blok siana";
        }
        if (material == Material.COAL_BLOCK) {
            return "blok wegla";
        }
        if (material == Material.PACKED_ICE) {
            return "zbity lod";
        }
        if (material == Material.IRON_PICKAXE) {
            return "zelazny kilof";
        }
        if (material == Material.IRON_AXE) {
            return "zelazna siekiera";
        }
        if (material == Material.FLINT_AND_STEEL) {
            return "zaplaniczka";
        }
        if (material == Material.APPLE) {
            return "jablko";
        }
        if (material == Material.BOW) {
            return "luk";
        }
        if (material == Material.ARROW) {
            return "strzala";
        }
        if (material == Material.COAL) {
            return "wegiel";
        }
        if (material == Material.DIAMOND) {
            return "diament";
        }
        if (material == Material.IRON_INGOT) {
            return "sztabka zelaza";
        }
        if (material == Material.GOLD_INGOT) {
            return "sztabka zlota";
        }
        if (material == Material.IRON_SWORD) {
            return "zelazny miecz";
        }
        if (material == Material.WOODEN_SWORD) {
            return "drewniany miecz";
        }
        if (material == Material.WOODEN_PICKAXE) {
            return "drewniany kilof";
        }
        if (material == Material.WOODEN_AXE) {
            return "drewniany toporek";
        }
        if (material == Material.STONE_SWORD) {
            return "kamienny miecz";
        }
        if (material == Material.STONE_PICKAXE) {
            return "kamienny kilof";
        }
        if (material == Material.STONE_AXE) {
            return "kamienny toporek";
        }
        if (material == Material.DIAMOND_SWORD) {
            return "diamentowy miecz";
        }
        if (material == Material.DIAMOND_PICKAXE) {
            return "diamentowy kilof";
        }
        if (material == Material.DIAMOND_AXE) {
            return "diamentowy toporek";
        }
        if (material == Material.STICK) {
            return "patyk";
        }
        if (material == Material.BOWL) {
            return "miska";
        }
        if (material == Material.GOLDEN_SWORD) {
            return "zloty miecz";
        }
        if (material == Material.GOLDEN_PICKAXE) {
            return "zloty kilof";
        }
        if (material == Material.GOLDEN_AXE) {
            return "zloty toporek";
        }
        if (material == Material.STRING) {
            return "nic";
        }
        if (material == Material.FEATHER) {
            return "piorko";
        }
        if (material == Material.GUNPOWDER) {
            return "proch";
        }
        if (material == Material.WOODEN_HOE) {
            return "drewniana motyka";
        }
        if (material == Material.STONE_HOE) {
            return "kamienna motyka";
        }
        if (material == Material.IRON_HOE) {
            return "zelazna motyka";
        }
        if (material == Material.DIAMOND_HOE) {
            return "diamentowa motyka";
        }
        if (material == Material.GOLDEN_HOE) {
            return "zlota motyka";
        }
        if (material == Material.WHEAT) {
            return "siano";
        }
        if (material == Material.BREAD) {
            return "chleb";
        }
        if (material == Material.LEATHER_HELMET) {
            return "skorzany helm";
        }
        if (material == Material.LEATHER_CHESTPLATE) {
            return "skorzana klata";
        }
        if (material == Material.LEATHER_LEGGINGS) {
            return "skorzane spodnie";
        }
        if (material == Material.LEATHER_BOOTS) {
            return "skorzane buty";
        }
        if (material == Material.CHAINMAIL_HELMET) {
            return "kolczaty helm";
        }
        if (material == Material.CHAINMAIL_CHESTPLATE) {
            return "kolczata klata";
        }
        if (material == Material.CHAINMAIL_LEGGINGS) {
            return "kolczate spodnie";
        }
        if (material == Material.CHAINMAIL_BOOTS) {
            return "kolczate buty";
        }
        if (material == Material.IRON_HELMET) {
            return "zelazny helm";
        }
        if (material == Material.IRON_CHESTPLATE) {
            return "zelazna klata";
        }
        if (material == Material.IRON_LEGGINGS) {
            return "zelazne spodnie";
        }
        if (material == Material.IRON_BOOTS) {
            return "zelazne buty";
        }
        if (material == Material.DIAMOND_HELMET) {
            return "diamentowy helm";
        }
        if (material == Material.DIAMOND_CHESTPLATE) {
            return "diamentowa klata";
        }
        if (material == Material.DIAMOND_LEGGINGS) {
            return "diamentowe spodnie";
        }
        if (material == Material.DIAMOND_BOOTS) {
            return "diamentowe buty";
        }
        if (material == Material.GOLDEN_HELMET) {
            return "zloty helm";
        }
        if (material == Material.GOLDEN_CHESTPLATE) {
            return "zlota klata";
        }
        if (material == Material.GOLDEN_LEGGINGS) {
            return "zlote spodnie";
        }
        if (material == Material.GOLDEN_BOOTS) {
            return "zlote buty";
        }
        if (material == Material.FLINT) {
            return "krzemien";
        }
        if (material == Material.PAINTING) {
            return "malowidlo";
        }
        if (material == Material.GOLDEN_APPLE) {
            if (dur == 0) {
                return "zlote jablko";
            }
            return "kox";
        }
        else {
            if (material == Material.BUCKET) {
                return "wiadro";
            }
            if (material == Material.WATER_BUCKET) {
                return "wiadro wody";
            }
            if (material == Material.LAVA_BUCKET) {
                return "wiadro lavy";
            }
            if (material == Material.MINECART) {
                return "wozek";
            }
            if (material == Material.SADDLE) {
                return "siodlo";
            }
            if (material == Material.IRON_DOOR) {
                return "zelazne drzwi";
            }
            if (material == Material.REDSTONE) {
                return "czerwony proch";
            }
            if (material == Material.SNOWBALL) {
                return "sniezka";
            }
            if (material == Material.LEATHER) {
                return "skora";
            }
            if (material == Material.MILK_BUCKET) {
                return "wiadro mleka";
            }
            if (material == Material.CLAY_BALL) {
                return "glina";
            }
            if (material == Material.SUGAR_CANE) {
                return "trzcina curkowa";
            }
            if (material == Material.PAPER) {
                return "papier";
            }
            if (material == Material.BOOK) {
                return "ksiazka";
            }
            if (material == Material.SLIME_BALL) {
                return "kula glutu";
            }
            if (material == Material.EGG) {
                return "jajo srutupufa";
            }
            if (material == Material.COMPASS) {
                return "kompas";
            }
            if (material == Material.FISHING_ROD) {
                return "wedka";
            }
            if (material == Material.CLOCK) {
                return "zegar";
            }
            if (material == Material.GLOWSTONE_DUST) {
                return "jasnopyl";
            }
            if (material == Material.BONE) {
                return "kosc";
            }
            if (material == Material.SUGAR) {
                return "cukier";
            }
            if (material == Material.CAKE) {
                return "ciasto";
            }
            if (material == Material.COOKIE) {
                return "ciasteczko";
            }
            if (material == Material.MAP) {
                return "mapa";
            }
            if (material == Material.SHEARS) {
                return "nozyce";
            }
            if (material == Material.MELON) {
                return "arbuz";
            }
            if (material == Material.PUMPKIN_SEEDS) {
                return "nasienie dyni";
            }
            if (material == Material.MELON_SEEDS) {
                return "nasienie arbuza";
            }
            if (material == Material.COOKED_BEEF) {
                return "befsztyk";
            }
            if (material == Material.COOKED_CHICKEN) {
                return "kurczak z rozna";
            }
            if (material == Material.ROTTEN_FLESH) {
                return "zgnila stopa srutupufa";
            }
            if (material == Material.ENDER_PEARL) {
                return "oko endermana";
            }
            if (material == Material.BLAZE_ROD) {
                return "rozdzka plomienia";
            }
            if (material == Material.GHAST_TEAR) {
                return "lza ducha";
            }
            if (material == Material.GOLD_NUGGET) {
                return "fragment zlotej protezy";
            }
            if (material == Material.POTION) {
                return "mikstura";
            }
            if (material == Material.GLASS_BOTTLE) {
                return "pusta butla 0,7";
            }
            if (material == Material.SPIDER_EYE) {
                return "oko pajaka";
            }
            if (material == Material.FERMENTED_SPIDER_EYE) {
                return "zgnile oko pajaka";
            }
            if (material == Material.BLAZE_POWDER) {
                return "plomienna koka";
            }
            if (material == Material.MAGMA_CREAM) {
                return "krem na przyszcze";
            }
            if (material == Material.WRITTEN_BOOK) {
                return "zapisana ksiazka";
            }
            if (material == Material.EMERALD) {
                return "szmaragd";
            }
            if (material == Material.ITEM_FRAME) {
                return "ramka na przedmiot";
            }
            if (material == Material.BAKED_POTATO) {
                return "pieczony zimniok";
            }
            if (material == Material.POISONOUS_POTATO) {
                return "trujacy zimniok";
            }
            if (material == Material.GOLDEN_CARROT) {
                return "zlota marchw";
            }
            if (material == Material.NETHER_STAR) {
                return "pentagram";
            }
            if (material == Material.PUMPKIN_PIE) {
                return "placek z dynii";
            }
            if (material == Material.ENCHANTED_BOOK) {
                return "zakleta ksiazka";
            }
            if (material == Material.QUARTZ) {
                return "kwarc";
            }
            if (material == Material.HOPPER_MINECART) {
                return "ssacy wozek";
            }
            if (material == Material.NAME_TAG) {
                return "niesmiertelnik";
            }
            return null;
        }
    }
}
