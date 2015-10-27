package org.mtforce.network;

import java.io.Serializable;

public class CmdPackage  implements Serializable 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -184098546949471233L;
	private boolean requestUpdate;

	public boolean isRequestUpdate() {
		return requestUpdate;
	}

	public void setRequestUpdate(boolean requestUpdate) {
		this.requestUpdate = requestUpdate;
	}
}
