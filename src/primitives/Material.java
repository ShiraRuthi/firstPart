/**
 * 
 */
package primitives;

/**
 * @author Ruthi
 *
 */
public class Material {

	    private double _kD;
	    private double _kS;
	    private int _nShininess=1;
	    private double _kR;
	    private double _kT;

	    /**
		 * @param _kD
		 * @param _kS
		 * @param _nShininess
		 * @param _kR
		 * @param _kT
		 */
		public Material(double _kD, double _kS, int _nShininess, double _kT, double _kR) {
			super();
			this._kD = _kD;
			this._kS = _kS;
			this._nShininess = _nShininess;
			this._kR = _kR;
			this._kT = _kT;
		}

		/**
		 * @param _kD
		 * @param _kS
		 * @param _nShininess
		 */
	    public Material(double _kD, double _kS, int _nShininess) {
	        this(_kD,_kS,_nShininess,0,0);
	    }

	    /**
	     * 
	     * @param material
	     */
//	    public Material(Material material) {
//	        this(material._kD, material._kS, material._nShininess);
//	    }
	    
	    public double getKd() {
	        return _kD;
	    }

	    public double getKs() {
	        return _kS;
	    }

	    /**
		 * @return the _kR
		 */
		public double get_kR() {
			return _kR;
		}

		/**
		 * @return the _kT
		 */
		public double get_kT() {
			return _kT;
		}

		public int getnShininess() {
	        return _nShininess;
	    }
}
