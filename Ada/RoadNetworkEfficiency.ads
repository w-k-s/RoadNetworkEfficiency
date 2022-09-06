with Ada.Text_IO; use Ada.Text_IO;

procedure Learn is
    
    type LatLng is record
       latitude : Float;
       longitude: Float;
    end record;
    
    type Km is new Float;

   function TotalLatitudinalAndLongitudinalDistance (LatLng1 : LatLng;
       LatLng2 : LatLng) return Km 
   is
       LatittudinalDifference: Float;
       LongitudinalDifference: Float;
    begin
       LatittudinalDifference := Float'Max(LatLng1.latitude, LatLng2.latitude) - Float'Min(LatLng1.latitude, LatLng2.latitude);
       LongitudinalDifference := Float'Max(LatLng1.longitude, LatLng2.longitude) - Float'Min(LatLng1.longitude, LatLng2.longitude);
       return Km(111.0*(LatittudinalDifference+LongitudinalDifference));
    end;
    
    LatLng1 : constant LatLng := (25.044349, 55.214259);
    LatLng2 : constant LatLng := (25.044319, 55.213599);
    Distance: Km;
begin
   Distance := TotalLatitudinalAndLongitudinalDistance(LatLng1, LatLng2);
    
   Put(Distance'Image); Put(" km "); New_Line(1);
end Learn;

