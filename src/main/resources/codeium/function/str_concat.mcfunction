data modify storage codeium:str_concat root.in set from storage codeium:str_concat root.in1
loot spawn 0 0 0 loot codeium:concat
execute positioned 0 0 0 run data modify storage codeium:str_concat root.escaped1 set string entity @n[type=item] Item.components.minecraft:lore[0] 1 -1
execute positioned 0 0 0 run kill @n[type=item]

data modify storage codeium:str_concat root.in set from storage codeium:str_concat root.in2
loot spawn 0 0 0 loot codeium:concat
execute positioned 0 0 0 run data modify storage codeium:str_concat root.escaped2 set string entity @n[type=item] Item.components.minecraft:lore[0] 1 -1
execute positioned 0 0 0 run kill @n[type=item]

function codeium:str_concat2 with storage codeium:str_concat root