package org.cigma.ecom.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Galerie implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    private int id;
    @ManyToOne(fetch = FetchType.LAZY)
    private Article article;
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] image;
}
