public class FrogCommands {
    public static FrogCommand jumpRightCommand(Frog frog, int steps) {
        return new FrogCommand() {
            @Override
            public boolean doit() {
                return frog.jump(steps);
            }

            @Override
            public boolean undo() {
                return frog.jump(-steps);
            }
        };
    }

    public static FrogCommand jumpLeftCommand(Frog frog, int steps) {
        return new FrogCommand() {
            @Override
            public boolean doit() {
                return frog.jump(-steps);
            }

            @Override
            public boolean undo() {
                return frog.jump(steps);
            }
        };
    }
}