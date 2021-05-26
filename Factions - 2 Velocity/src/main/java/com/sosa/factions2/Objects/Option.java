package com.sosa.factions2.Objects;

import java.util.HashMap;

/**
 * This class is used to list all config options and their default values.
 */
public enum Option {
    MINIMUM_TAG_LENGTH,
    MAXIMUM_TAG_LENGTH;

    //These are the values of each option.
    private static HashMap<Option, Object> options = new HashMap<>();

    /**
     * This method returns the default value of each option.
     *
     * @param option This is the option to return the default value for.
     *
     * @return The default value of this option.
     */
    public static Object getDefaultValue(Option option)
    {
        switch (option)
        {
            case MINIMUM_TAG_LENGTH:
                return 3;
            case MAXIMUM_TAG_LENGTH:
                return 12;
        }

        return null;
    }

    /**
     * This method returns the mapping of all config options.
     *
     * @return The mapping of all config options.
     */
    public static HashMap<Option, Object> getOptions()
    {
        return options;
    }
}
