package ru.job4j.assertj;

import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BoxTest {

    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere")
                .isNotEmpty()
                .isNotBlank()
                .containsIgnoringCase("sphere")
                .contains("Sphere")
                .doesNotContain("cube")
                .startsWith("Sph")
                .startsWithIgnoringCase("s")
                .endsWith("ere");

    }

    @Test
    void isThisCube() {
        Box box = new Box(6, 8);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Cube")
                .isNotEmpty()
                .isNotBlank()
                .containsIgnoringCase("cube")
                .contains("Cube")
                .doesNotContain("sphere")
                .startsWith("Cu")
                .startsWithIgnoringCase("c")
                .endsWith("be");
    }

    @Test
    void isThisUnknown() {
        Box box = new Box(1, 6);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Unknown object")
                .doesNotContain("cube");
    }

    @Test
    void whenEdgeNull() {
        Box box = new Box(0, 0);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Unknown object");
    }

    @Test
    void numberOfVerticesTetrahedron() {
        Box box = new Box(4, 4);
        int count = box.getNumberOfVertices();
        assertThat(count).isEqualTo(4)
                .isPositive()
                .isEven()
                .isGreaterThan(3)
                .isLessThan(5);
    }

    @Test
    void numberOfVerticesWrong() {
        Box box = new Box(3, 4);
        int count = box.getNumberOfVertices();
        assertThat(count).isEqualTo(-1)
                .isNegative();
    }

    @Test
    void numberOfEdgeNull() {
        Box box = new Box(6, 0);
        int count = box.getNumberOfVertices();
        assertThat(count).isEqualTo(-1)
                .isNegative();
    }

    @Test
    void sphereIsExist() {
        Box box = new Box(0, 4);
        boolean result = box.isExist();
        assertThat(result).isEqualTo(true);
    }

    @Test
    void isUnknown() {
        Box box = new Box(3, 4);
        boolean result = box.isExist();
        assertThat(result).isEqualTo(false);
    }

    @Test
    void isEdgeNull() {
        Box box = new Box(6, 0);
        boolean result = box.isExist();
        assertThat(result).isEqualTo(false);
    }

    @Test
    void getAreaSphere() {
        Box box = new Box(0, 4);
        double area = box.getArea();
        assertThat(area).isEqualTo(201.061d, withPrecision(0.001d))
                .isCloseTo(201.061d, withPrecision(0.01d))
                .isCloseTo(201.061d, Percentage.withPercentage(1.0d))
                .isGreaterThan(201.051d)
                .isLessThan(201.071d);
    }
}