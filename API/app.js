require('dotenv').config();
const { OpenAI} = require("openai");
const key = process.env.OPENAI_API_KEY;

const openai = new OpenAI({
    apiKey : key
});


const express = require('express');
const app = express();
const port = process.env.PORT || 3000;

app.get('/data', async (req, res) => {
    // Accessing the query string values
    const { title,description,time } = req.query;
    let messages = [
        { role: "system", content: `You are tasked with transforming daily activities into classic Isekai-style adventures. 
        For each task input with a title, description, and time, you must reimagine it as a quest complete with a title, 
        a narrative description, experience points, and gold rewards. Make the fantasy title around the same length as the origional one.
        Below is the format for how you should calculate and present the output. Your response needs to be a json.
        input={"title": "Wash Dishes","description: My mom wants me to wash the dishes","time": "5:00 P.M."}
        {"fantasyTitle": "The Quest of the Gleaming Vessels","fantasyDescription": "Embark on the epic quest to purify the Ancient Ceramics guarded by the old kitchen. Legend speaks of the enchanted suds that turn the mundane task into a battle for honor and glory.","expReward": 50,"goldReward": 10}` },
        {role: "user", content: `input = {"title": "${title}", "description: ${description}", "time": "${time}"}`
    }];
    try {
        const completion = await openai.chat.completions.create({
            messages: messages,
            model: "gpt-4"
          });
        // console.log(completion.choices[0]);
        const content = completion.choices[0].message.content;
        const parsedContent = JSON.parse(content);
        parsedContent.time = time
        res.json(parsedContent);

    } catch (error) {
        console.error("Error calling OpenAI", error);
        res.status(500).send("Error processing your request")
    }
});


// Start the server
app.listen(port, () => {
    console.log(`Server running on port ${port}`);
});
