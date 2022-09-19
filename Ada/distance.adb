package body Distance is

-- The distance between the two points if a grid like road network existed
function CityBlockDistance (LatLng1 : LatLng; LatLng2 : LatLng) return Km is
   LatittudinalDifference: Float;
   LongitudinalDifference: Float;
begin
   LatittudinalDifference := Float'Max(LatLng1.Lat, LatLng2.Lat) - Float'Min(LatLng1.Lat, LatLng2.Lat);
   LongitudinalDifference := Float'Max(LatLng1.Lng, LatLng2.Lng) - Float'Min(LatLng1.Lng, LatLng2.Lng);
   return Km(111.0*(LatittudinalDifference+LongitudinalDifference));
end;

end Distance;