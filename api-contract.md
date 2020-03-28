GET /targets 
// backend assume that user always the same, auth not required.
--> 
```
{
	currentLocatioon: {  // optional, default: customer's address
		latitude: integer
		longitude: integer
	},
	targetType: string, // enum: ["shop", "pharmacy", ...], optional - default: "shop",
}
```
<--
```
{
	id: integer, // slots could be fetched by this id as target_id
	name: string,
	distance: integer // distance in meters from you,
	slotsPreviews: [
		{
			startDate: date, // to sort by them,
			freeCapacity: integer // how many people can join current slot
		}
	]
} 
```
