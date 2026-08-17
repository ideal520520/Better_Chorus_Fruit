# Better_Chorus_Fruit

Enhances Chorus Fruit teleportation: when a player eats it in the void, the target Y is randomly chosen between the "void top" and the "build height limit", while XZ keeps the vanilla 8-block Chebyshev distance.

## Features

- **Trigger condition**: a Player eats Chorus Fruit while in the void, i.e. the player's Y coordinate is ≤ the void threshold of the current dimension
- **Y selection**: uniform random within the whole `[void top, build height limit]` range
- **XZ selection**: vanilla behavior, random within an 8-block Chebyshev distance around the player
- **Target validation**: fully uses the vanilla rules (target chunk loaded, search downward for a solid block, target spot free of collisions and fluids), up to 16 attempts
- **Other cases**: non-player entities, non-void situations and other dimensions keep the vanilla behavior

Dimension values:

| Dimension | Void threshold (Y ≤) | Y random range |
|-----------|----------------------|----------------|
| Overworld | -128 | [-128, 320] |
| Nether | -64 | [-64, 128] |
| End | -64 | [-64, 256] |

## Installation

Teleportation is server-authoritative, so the mod **must be installed on the server**; installing it on the client is optional.

- Singleplayer: installing on the client is enough
- Multiplayer: install on the server; the client may or may not have it installed

## License

GPL-3.0
