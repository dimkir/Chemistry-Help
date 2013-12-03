package com.pk.chemhelp.lawxmlparser;

import java.util.List;

/**
 * This is polymorphic interface for parser of the law elements.
 * 
 * @author Ernesto Guevara
 *
 */
public interface ILawParser {
	List<LawElement> getLawElements();
}
