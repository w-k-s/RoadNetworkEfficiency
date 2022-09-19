with Geo; use Geo;

package Distance is

type Km is new Float;

function CityBlockDistance (LatLng1 : LatLng; LatLng2 : LatLng) return Km;

end Distance;