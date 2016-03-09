package com.octo.bof.crunch.level2;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Powered by o<+o
 */

public class ExcludeFileFilterUTest {

    private ExcludeFileFilter excludeFileFilter;

    @Before
    public void setUp() throws Exception {
        excludeFileFilter = new ExcludeFileFilter();
    }

    @Test
    public void accept_shouldReturnTrue(){
        // Given
        String input = "/url/page";

        // When
        boolean result = excludeFileFilter.accept(input);

        // Then
        assertThat(result).isTrue();
    }

    @Test
    public void accept_shouldReturnFalse_whenInputEndsWithJPG(){
        // Given
        String input = "/url/image.jpg";

        // When
        boolean result = excludeFileFilter.accept(input);

        // Then
        assertThat(result).isFalse();
    }

    @Test
    public void accept_shouldReturnFalse_whenInputEndsWithGIF(){
        // Given
        String input = "/url/image.gif";

        // When
        boolean result = excludeFileFilter.accept(input);

        // Then
        assertThat(result).isFalse();
    }

    @Test
    public void accept_shouldReturnFalse_whenInputEndsWithICO(){
        // Given
        String input = "/url/image.ico";

        // When
        boolean result = excludeFileFilter.accept(input);

        // Then
        assertThat(result).isFalse();
    }

    @Test
    public void accept_shouldReturnFalse_whenInputEndsWithTXT(){
        // Given
        String input = "/url/image.txt";

        // When
        boolean result = excludeFileFilter.accept(input);

        // Then
        assertThat(result).isFalse();
    }

    @Test
    public void accept_shouldReturnFalse_whenInputEndsWithPNG(){
        // Given
        String input = "/url/image.png";

        // When
        boolean result = excludeFileFilter.accept(input);

        // Then
        assertThat(result).isFalse();
    }
}
