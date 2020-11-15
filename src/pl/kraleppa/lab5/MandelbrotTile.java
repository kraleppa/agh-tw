package pl.kraleppa.lab5;

import java.util.concurrent.Future;

public record MandelbrotTile(int x, int y, Future<Integer> value) { }
